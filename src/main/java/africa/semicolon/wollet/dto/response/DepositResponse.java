package africa.semicolon.wollet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositResponse {
    private Long transactionId;
    private  String message;
    private String amount;
}
