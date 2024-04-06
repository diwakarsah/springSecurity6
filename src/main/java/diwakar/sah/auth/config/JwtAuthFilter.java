package diwakar.sah.auth.config;

import diwakar.sah.auth.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

   @Autowired
   private JwtService jwtService;

   @Autowired
   UserDetailsServiceImpl userDetailsServiceImpl;


   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      String authHeader = request.getHeader("Authorization");
      String token = null;
      String username = null;
      if(authHeader != null && authHeader.startsWith("Bearer ")){
         token = authHeader.substring(7);
         username = jwtService.extractUsername(token);
      }

      if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
         UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
         if(jwtService.validateToken(token, userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }

      }

      filterChain.doFilter(request, response);
   }
}