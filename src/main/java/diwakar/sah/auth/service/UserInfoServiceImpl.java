package diwakar.sah.auth.service;

import diwakar.sah.auth.*;
import diwakar.sah.auth.RequestDto.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

   public final UserRepository userRepository;
   public final PasswordEncoder passwordEncoder;

   @Override
   public void signup(AuthRequestDTO authRequestDTO) {
      var user = new UserInfo();
      user.setUsername(authRequestDTO.getUsername());
      user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
      userRepository.save(user);
   }
}
