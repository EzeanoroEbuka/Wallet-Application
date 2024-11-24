package africa.semicolon.wollet.dto.response;

import africa.semicolon.wollet.models.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetCustomerResponse {
    private String message;
    private Customer customer;
}
