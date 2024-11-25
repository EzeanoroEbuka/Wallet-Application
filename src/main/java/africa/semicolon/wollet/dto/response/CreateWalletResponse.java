package africa.semicolon.wollet.dto.response;

import africa.semicolon.wollet.models.Wallet;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateWalletResponse {
    private Wallet wallet;
    private String message;
}
