package africa.semicolon.wollet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WalletDepositRequest {
    private Long walletId;
    private BigDecimal amount;
}
