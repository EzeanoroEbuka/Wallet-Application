package africa.semicolon.wollet.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTransactionResponse {
    private Long id;
    private String recipientAccount;
    private String senderAccount;
    private BigDecimal amount;
    private String status;
    private String description;
}