package diwakar.sah.auth.RequestDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
   private String accessToken;

}