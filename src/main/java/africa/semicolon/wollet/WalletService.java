package africa.semicolon.wollet;

import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;

public interface WalletService {

    WalletDepositResponse deposit(WalletDepositRequest deposit);
}

