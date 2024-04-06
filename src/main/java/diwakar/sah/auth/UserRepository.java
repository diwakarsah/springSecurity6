package diwakar.sah.auth;

import diwakar.sah.auth.RequestDto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
   public UserInfo findByUsername(String username);
}
