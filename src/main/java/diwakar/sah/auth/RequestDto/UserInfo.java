package diwakar.sah.auth.RequestDto;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@lombok.Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserInfo {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private long id;
   private String username;
   @JsonIgnore
   private String password;
//   @ManyToMany(fetch = FetchType.EAGER)
//   private Set<UserRole> roles = new HashSet<>();

}
