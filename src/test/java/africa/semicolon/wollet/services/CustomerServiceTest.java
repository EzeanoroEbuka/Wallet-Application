package africa.semicolon.wollet.services;

import africa.semicolon.wollet.dto.request.GetCustomerRequest;
import africa.semicolon.wollet.dto.request.UpdateCustomerRequest;
import africa.semicolon.wollet.dto.response.CustomerRegistrationResponse;
import africa.semicolon.wollet.dto.request.CustomerRegistrationRequest;
import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;
import africa.semicolon.wollet.dto.response.GetCustomerResponse;
import africa.semicolon.wollet.dto.response.UpdateResponse;
import africa.semicolon.wollet.exception.CustomerEmailAlreadyExistException;
import africa.semicolon.wollet.exception.TransactionNotFoundException;
import africa.semicolon.wollet.exception.USerNotFoundException;
import africa.semicolon.wollet.exception.WalletNotFoundException;
import africa.semicolon.wollet.models.Transaction;
import africa.semicolon.wollet.service.CustomerService;
import africa.semicolon.wollet.service.TransactionServiceImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static africa.semicolon.wollet.dto.response.TransactionStatus.SUCCESS;
import static africa.semicolon.wollet.models.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionServiceImp transactionService;


    @Test
    public void testCustomerCanDeposit() throws WalletNotFoundException, USerNotFoundException {
        Long customerId = 2L;
        BigDecimal amount = new BigDecimal("100.00");
        String description = "description";
        CustomerDepositRequest request = new CustomerDepositRequest();
        mapper(request, customerId, amount, description);
        DepositResponse response = customerService.deposit(request);

        assertNotNull(response);
        assertNotNull(response.getMessage());
        assertEquals("SUCCESSFUL", response.getMessage());
    }


    @Test
    public void testTransactionIsCreatedForDeposit() throws WalletNotFoundException, USerNotFoundException, TransactionNotFoundException {
        Long customerId = 2L;
        BigDecimal amount = new BigDecimal("100.00");
        String description = "description";
        CustomerDepositRequest request = new CustomerDepositRequest();
        mapper(request, customerId, amount, description);
        DepositResponse response = customerService.deposit(request);
        Transaction transaction  =transactionService.getTransaction(response.getTransactionId());
        assertNotNull(response);
        assertNotNull(transaction);
        assertEquals(SUCCESS,transaction.getStatus());
        System.out.println(transaction);
    }


    @Test
    public void testThatCustomerIsRegistered() throws CustomerEmailAlreadyExistException {
        CustomerRegistrationRequest request = createCustomerRequest();
        CustomerRegistrationResponse response = customerService.registerCustomer(request);
        assertThat(response).isNotNull();
        assertEquals("SUCCESSFUL", response.getMessage());
    }

    @Test
    public void testThatNoDuplicateCustomerWithSameEmail() throws CustomerEmailAlreadyExistException {
        CustomerRegistrationRequest request = createCustomerRequest();
        CustomerRegistrationResponse response = customerService.registerCustomer(request);
        assertThat(response).isNotNull();
        assertEquals("SUCCESSFUL", response.getMessage());
        CustomerEmailAlreadyExistException exception = assertThrows(CustomerEmailAlreadyExistException.class,
                () -> customerService.registerCustomer(request));
        assertEquals("Email Already Exist", exception.getMessage());
    }

    @Test
    public void  testThatCustomerIsFoundByID() throws USerNotFoundException {
        Long customerId = 2L;
        GetCustomerRequest request = new GetCustomerRequest();
        request.setCustomerId(customerId);
        GetCustomerResponse response = customerService.getCustomer(request);
        assertEquals(customerId,response.getCustomer().getId());
    }


    @Test
    public void testThatCustomerDetailsAreUpdated() throws USerNotFoundException {
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setId(2L);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setGender(MALE);
        request.setEmail("john@doe.com");
        request.setPassword("1990");
        UpdateResponse response = customerService.updateCustomer(request);
        assertEquals(response.getEmail(),"john@doe.com");
        assertEquals(response.getPassword(),"1990");
    }

    private static void mapper(CustomerDepositRequest request, Long customerId, BigDecimal amount, String description) {
        request.setCustomerId(customerId);
        request.setAmount(amount);
        request.setDescription(description);
    }

    private static CustomerRegistrationRequest createCustomerRequest() {
        CustomerRegistrationRequest request = new CustomerRegistrationRequest();
        request.setFirstName("King");
        request.setLastName("Baker");
        request.setEmail("king@baker.com");
        request.setPassword("password");
        request.setGender(MALE);
        return request;
    }
}
