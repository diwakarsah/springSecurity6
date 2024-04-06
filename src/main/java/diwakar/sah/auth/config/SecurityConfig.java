package diwakar.sah.auth.config;

import diwakar.sah.auth.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

   @Autowired
   JwtAuthFilter jwtAuthFilter;

   @Bean
   public UserDetailsService userDetailsService() {
      return new UserDetailsServiceImpl();
   }


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http .csrf(AbstractHttpConfigurer::disable)
              .cors(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(requests -> requests
                      .requestMatchers("/api/v1/login","/api/v1/signup").permitAll()
                      .anyRequest().authenticated())

//              .authorizeHttpRequests(requests->requests.requestMatchers("/api/v1/**").authenticated())
              .sessionManagement(res->res.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authenticationProvider(authenticationProvider())
              .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService());
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;

   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
   }
}
