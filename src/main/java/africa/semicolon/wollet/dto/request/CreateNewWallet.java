package africa.semicolon.wollet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateNewWallet {
    private BigDecimal balance;
    private String accountNumber;
}
