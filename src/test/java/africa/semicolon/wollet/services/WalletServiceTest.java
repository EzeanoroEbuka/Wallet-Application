package africa.semicolon.wollet.services;

import africa.semicolon.wollet.service.WalletService;
import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;
import africa.semicolon.wollet.exception.WalletNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static africa.semicolon.wollet.dto.response.TransactionStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void testCanDepositIntoWallet() throws WalletNotFoundException {
        Long walletId = 200L;
        BigDecimal depositAmount = new BigDecimal("100.00");
        WalletDepositRequest deposit = new WalletDepositRequest();
        deposit.setAmount(depositAmount);
        deposit.setWalletId(walletId);
        WalletDepositResponse response = walletService.deposit(deposit);

        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertEquals(SUCCESS.toString(), response.getStatus());
    }

    @Test
    public void testCanWithdrawIntoWallet() throws WalletNotFoundException {
        Long walletId = 200L;
        BigDecimal withdrawAmount = new BigDecimal("100.00");

    }

}
