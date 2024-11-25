package africa.semicolon.wollet.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(unique=true)
    private String accountNumber;



    public Wallet(){
        balance = BigDecimal.ZERO;
    }
}
