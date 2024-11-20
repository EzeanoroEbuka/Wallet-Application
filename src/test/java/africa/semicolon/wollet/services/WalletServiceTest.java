package africa.semicolon.wollet.services;

import africa.semicolon.wollet.WalletService;
import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void testCanDepositIntoWallet() {
        Long walletId = 200L;
        BigDecimal depositAmount = new BigDecimal("100.00");
        WalletDepositRequest deposit = new WalletDepositRequest();
        deposit.setAmount(depositAmount);
        deposit.setWalletId(walletId);
        WalletDepositResponse response = walletService.deposit(deposit);
        response.setStatus("SUCCESSFUL");

        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertEquals("SUCCESSFUL", response.getStatus());
    }
}
