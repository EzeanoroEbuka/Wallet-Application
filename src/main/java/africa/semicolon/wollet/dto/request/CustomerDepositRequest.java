package africa.semicolon.wollet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CustomerDepositRequest {
    private Long customerId;
    private BigDecimal amount;
    private String description;

}
