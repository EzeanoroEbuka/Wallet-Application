package africa.semicolon.wollet.dto.request;

import africa.semicolon.wollet.dto.response.TransactionStatus;
import africa.semicolon.wollet.models.TransactionType;
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
    private TransactionType type;
    private String description;
}
