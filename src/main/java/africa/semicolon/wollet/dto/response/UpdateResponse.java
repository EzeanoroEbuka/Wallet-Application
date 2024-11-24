package africa.semicolon.wollet.dto.response;

import africa.semicolon.wollet.models.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateResponse {
   private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
    private String message;

}
