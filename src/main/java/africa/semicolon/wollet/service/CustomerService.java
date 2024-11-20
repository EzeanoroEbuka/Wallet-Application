package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;

public interface CustomerService {

    DepositResponse deposit(CustomerDepositRequest request);
}
