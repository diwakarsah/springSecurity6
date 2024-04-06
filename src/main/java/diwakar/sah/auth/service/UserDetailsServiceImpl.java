package diwakar.sah.auth.service;

import diwakar.sah.auth.*;
import diwakar.sah.auth.RequestDto.*;
import diwakar.sah.auth.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      logger.debug("Entering in loadUserByUsername Method...");
      UserInfo user = userRepository.findByUsername(username);
      if(user == null){
         logger.error("Username not found: " + username);
         throw new UsernameNotFoundException("could not found user..!!");
      }
      logger.info("User Authenticated Successfully..!!!");
      return new CustomUserDetails(user);
   }
}