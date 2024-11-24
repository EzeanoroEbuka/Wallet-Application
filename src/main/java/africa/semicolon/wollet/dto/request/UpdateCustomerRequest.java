package africa.semicolon.wollet.dto.request;

import africa.semicolon.wollet.models.Gender;
import africa.semicolon.wollet.models.Wallet;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UpdateCustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String password;



}
