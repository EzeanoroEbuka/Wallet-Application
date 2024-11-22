package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;
import africa.semicolon.wollet.exception.USerNotFoundException;
import africa.semicolon.wollet.exception.WalletNotFoundException;

public interface CustomerService {

    DepositResponse deposit(CustomerDepositRequest request) throws USerNotFoundException, WalletNotFoundException;
}
