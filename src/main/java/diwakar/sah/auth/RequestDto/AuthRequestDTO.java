package diwakar.sah.auth.RequestDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {

   private String username;
   private String password;
}

