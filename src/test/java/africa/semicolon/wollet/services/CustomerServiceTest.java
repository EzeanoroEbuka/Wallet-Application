package africa.semicolon.wollet.services;

import africa.semicolon.wollet.dto.response.DepositResponse;
import africa.semicolon.wollet.dto.request.CustomerDepositRequest;
import africa.semicolon.wollet.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCustomerCanDeposit() {
        Long customerId = 200L;
        BigDecimal amount = new BigDecimal("100.00");
        String description = "description";
        CustomerDepositRequest request = new CustomerDepositRequest();
        request.setCustomerId(customerId);
        request.setAmount(amount);
        request.setDescription(description);
        DepositResponse response = customerService.deposit(request);

        assertNotNull(response);
        assertNotNull(response.getMessage());
        assertEquals("SUCCESSFUL", response.getMessage());
    }

}
