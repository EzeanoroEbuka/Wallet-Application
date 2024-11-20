package africa.semicolon.wollet.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WalletDepositResponse {
    private String status;
    private String amount;
}
