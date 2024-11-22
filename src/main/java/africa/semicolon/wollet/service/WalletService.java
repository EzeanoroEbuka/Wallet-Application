package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;
import africa.semicolon.wollet.exception.WalletNotFoundException;

public interface WalletService {

    WalletDepositResponse deposit(WalletDepositRequest deposit) throws WalletNotFoundException;
}
