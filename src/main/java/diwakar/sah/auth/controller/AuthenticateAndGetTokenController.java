package diwakar.sah.auth.controller;

import diwakar.sah.auth.RequestDto.*;
import diwakar.sah.auth.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class AuthenticateAndGetTokenController {

   private final JwtService jwtService;
   private final AuthenticationManager authenticationManager;
   private final UserInfoService userInfoService;


   @PostMapping("/api/v1/login")
   public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
      if(authentication.isAuthenticated()){
         return JwtResponseDTO.builder()
                 .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
      } else {
         throw new UsernameNotFoundException("invalid user request..!!");
      }
   }

   @PostMapping("/api/v1/signup")
   public ResponseEntity<String> createUser(@RequestBody AuthRequestDTO authRequestDTO){
      userInfoService.signup(authRequestDTO);
      return  ResponseEntity.ok("Successfully");
   }
}
