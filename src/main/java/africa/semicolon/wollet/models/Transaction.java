package africa.semicolon.wollet.models;

import africa.semicolon.wollet.dto.response.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientAccount;
    private String senderAccount;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String description;

}
