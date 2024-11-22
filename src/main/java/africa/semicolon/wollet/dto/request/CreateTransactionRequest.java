package africa.semicolon.wollet.dto.request;

import africa.semicolon.wollet.dto.response.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTransactionRequest {
    private String recipientAccount;
    private String senderAccount;
    private BigDecimal amount;
    private TransactionStatus status;
    private String description;

}
