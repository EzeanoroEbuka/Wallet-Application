package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.request.*;
import africa.semicolon.wollet.dto.response.*;
import africa.semicolon.wollet.exception.CustomerEmailAlreadyExistException;
import africa.semicolon.wollet.exception.USerNotFoundException;
import africa.semicolon.wollet.exception.WalletNotFoundException;
import africa.semicolon.wollet.models.Customer;
import africa.semicolon.wollet.models.Wallet;
import africa.semicolon.wollet.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final WalletService walletService;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;

    @Transactional
    @Override
    public CustomerRegistrationResponse registerCustomer(CustomerRegistrationRequest request) throws CustomerEmailAlreadyExistException {
        validateCustomerEmailUniqueness(request);
        CreateWalletResponse newWallet =walletService.createNewWallet(mapNewWallet());
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setWallet(newWallet.getWallet());
        customer = customerRepository.save(customer);
        var response = modelMapper.map(customer, CustomerRegistrationResponse.class);
        response.setMessage("SUCCESSFUL");
        return response;
    }

    @Override
    public GetCustomerResponse getCustomer(GetCustomerRequest request) throws USerNotFoundException {
        Customer customer = findCustomerById(request.getCustomerId());
        return modelMapper.map(customer, GetCustomerResponse.class);
    }

    @Override
    public UpdateResponse updateCustomer(UpdateCustomerRequest request) throws USerNotFoundException {
        Customer customer = findCustomerById(request.getId());
        conditionalCheckAndMapUpdateRequest(request, customer);
        customer = customerRepository.save(customer);
        var response = modelMapper.map(customer, UpdateResponse.class);
        response.setMessage("SUCCESSFUL");
        return response;
    }


    @Override
    public DepositResponse deposit(CustomerDepositRequest request) throws USerNotFoundException, WalletNotFoundException {
        Customer customer = findCustomerById(request.getCustomerId());
        Wallet customerWallet = customer.getWallet();
        WalletDepositRequest walletDepositRequest = createWalletDepositRequestFrom(request, customerWallet);
        WalletDepositResponse response = walletService.deposit(walletDepositRequest);

        CreateTransactionRequest transactionRequest = transactionMapping(request, new CreateTransactionRequest(), customerWallet, response);
        CreateTransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return mapDepositResponse(response, transactionResponse);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        long randomNumber = (random.nextLong(10000000,999999999));
        return String.valueOf(randomNumber);

    }

    private CreateNewWallet mapNewWallet() {
        CreateNewWallet createNewWalletRequest = new CreateNewWallet();
        createNewWalletRequest.setBalance(new BigDecimal("0.00"));
        createNewWalletRequest.setAccountNumber(generateAccountNumber());
        return createNewWalletRequest;
    }

    private DepositResponse mapDepositResponse(WalletDepositResponse response, CreateTransactionResponse transactionResponse) {
        var depositResponse =  modelMapper.map(response, DepositResponse.class);
        depositResponse.setTransactionId(transactionResponse.getId());
        depositResponse.setMessage("SUCCESSFUL");
        return depositResponse;
    }

    private Customer findCustomerById(Long id) throws USerNotFoundException {
        return customerRepository.findById(id)
                                              .orElseThrow(()-> new USerNotFoundException(
                                              String.format("Customer with id %d not found", id)));

    }

    private static CreateTransactionRequest transactionMapping(CustomerDepositRequest request, CreateTransactionRequest transactionRequest, Wallet customerWallet, WalletDepositResponse response) {
        transactionRequest.setRecipientAccount(customerWallet.getAccountNumber());
        transactionRequest.setAmount(request.getAmount());
        transactionRequest.setStatus(TransactionStatus.valueOf(response.getStatus()));
        transactionRequest.setDescription(request.getDescription());
        transactionRequest.setSenderAccount("loading");
        return transactionRequest;
    }

    private static WalletDepositRequest createWalletDepositRequestFrom(CustomerDepositRequest request, Wallet customerWallet) {
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setWalletId(customerWallet.getId());
        walletDepositRequest.setAmount(request.getAmount());
        return walletDepositRequest;
    }

    private static void conditionalCheckAndMapUpdateRequest(UpdateCustomerRequest request, Customer customer) {
        if(request.getFirstName() != null){
            customer.setFirstName(request.getFirstName());}
        if(request.getLastName() != null){
            customer.setLastName(request.getLastName());}
        if(request.getPassword() != null){
            customer.setPassword(request.getPassword());}
        if(request.getEmail() != null){
            customer.setEmail(request.getEmail());}
        if(request.getGender() != null){
            customer.setGender(request.getGender());}
    }

    private void validateCustomerEmailUniqueness(CustomerRegistrationRequest request) throws CustomerEmailAlreadyExistException {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(request.getEmail());
        if(foundCustomer.isPresent()){
            throw new CustomerEmailAlreadyExistException("Email Already Exist");
        }
    }


}
