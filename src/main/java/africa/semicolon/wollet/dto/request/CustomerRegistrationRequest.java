package africa.semicolon.wollet.dto.request;

import africa.semicolon.wollet.models.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRegistrationRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
}
