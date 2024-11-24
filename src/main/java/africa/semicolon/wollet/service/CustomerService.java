package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.request.CustomerRegistrationRequest;
import africa.semicolon.wollet.dto.request.GetCustomerRequest;
import africa.semicolon.wollet.dto.request.UpdateCustomerRequest;
import africa.semicolon.wollet.dto.response.CustomerRegistrationResponse;
import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;
import africa.semicolon.wollet.dto.response.GetCustomerResponse;
import africa.semicolon.wollet.dto.response.UpdateResponse;
import africa.semicolon.wollet.exception.CustomerEmailAlreadyExistException;
import africa.semicolon.wollet.exception.USerNotFoundException;
import africa.semicolon.wollet.exception.WalletNotFoundException;

public interface CustomerService {

    DepositResponse deposit(CustomerDepositRequest request) throws USerNotFoundException, WalletNotFoundException;

    CustomerRegistrationResponse registerCustomer(CustomerRegistrationRequest request) throws CustomerEmailAlreadyExistException;

    GetCustomerResponse getCustomer(GetCustomerRequest request) throws USerNotFoundException;

    UpdateResponse updateCustomer(UpdateCustomerRequest request) throws USerNotFoundException;
}

