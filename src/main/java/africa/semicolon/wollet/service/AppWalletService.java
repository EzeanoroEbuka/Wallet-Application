package africa.semicolon.wollet.service;

import africa.semicolon.wollet.WalletService;
import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;
import africa.semicolon.wollet.exception.WallectNotFoundException;
import africa.semicolon.wollet.models.Wallet;
import africa.semicolon.wollet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppWalletService implements WalletService {

//    private final ModelMapper modelMapper;
    private final WalletRepository repository;

    @Override
    public WalletDepositResponse deposit(WalletDepositRequest deposit) {
       Wallet wallet =  repository.findById(deposit.getWalletId())
                            .orElseThrow(() ->  new WallectNotFoundException(
                               String.format("wallet with id %d not found",deposit.getWalletId())));

       wallet.setBalance(wallet.getBalance().add(deposit.getAmount()));
       repository.save(wallet);
       WalletDepositResponse response = new WalletDepositResponse();
       response.setStatus("SUCCESSFUL");
       response.setAmount(deposit.getAmount().toEngineeringString());
       return response;
    }
}
