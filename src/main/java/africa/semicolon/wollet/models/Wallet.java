package africa.semicolon.wollet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Wallet {
    @Id
    private Long id;
    private BigDecimal balance;
    private String accountNumber;



    public Wallet(){
        balance = BigDecimal.ZERO;
    }
}
