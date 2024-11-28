package africa.semicolon.wollet.services;

import africa.semicolon.wollet.dto.request.CreateTransactionRequest;
import africa.semicolon.wollet.dto.response.CreateTransactionResponse;
import africa.semicolon.wollet.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static africa.semicolon.wollet.dto.response.TransactionStatus.SUCCESS;
import static africa.semicolon.wollet.models.TransactionType.DEPOSIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class TransactionServiceTest {

   @Autowired
   private TransactionService transactionService;

   @Test
    public void testTransactionIsCreated() {
       CreateTransactionRequest transaction = new CreateTransactionRequest();
       setUpTransaction(transaction);
       CreateTransactionResponse response = transactionService.createTransaction(transaction);
       assertNotNull(response);
       assertEquals(SUCCESS.toString(), response.getStatus());
       assertEquals(DEPOSIT.toString(), response.getTransactionType());

   }

   private static void setUpTransaction(CreateTransactionRequest transaction) {
      transaction.setSenderAccount("12345");
      transaction.setDescription("test Transactions");
      transaction.setRecipientAccount("12131425");
      transaction.setAmount(new BigDecimal("100.00"));
      transaction.setType(DEPOSIT);
      transaction.setStatus(SUCCESS);
   }


}