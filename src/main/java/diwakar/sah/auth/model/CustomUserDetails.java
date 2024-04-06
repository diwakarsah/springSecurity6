package diwakar.sah.auth.model;

import diwakar.sah.auth.RequestDto.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class CustomUserDetails extends UserInfo implements UserDetails {

  private final UserInfo userInfo;
   Collection<? extends GrantedAuthority> authorities;

   public CustomUserDetails(UserInfo byUsername) {
      this.userInfo = byUsername;
      List<GrantedAuthority> auths = new ArrayList<>();

//      for(UserRole role : byUsername.getRoles()){
//
//         auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
//      }
      this.authorities = auths;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }

   @Override
   public String getPassword() {
      return userInfo.getPassword();
   }

   @Override
   public String getUsername() {
      return userInfo.getUsername();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
