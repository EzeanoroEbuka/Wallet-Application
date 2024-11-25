package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.request.CreateNewWallet;
import africa.semicolon.wollet.dto.request.WalletDepositRequest;
import africa.semicolon.wollet.dto.response.CreateWalletResponse;
import africa.semicolon.wollet.dto.response.WalletDepositResponse;
import africa.semicolon.wollet.exception.WalletNotFoundException;
import africa.semicolon.wollet.models.Wallet;
import africa.semicolon.wollet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static africa.semicolon.wollet.dto.response.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public WalletDepositResponse deposit(WalletDepositRequest deposit) throws WalletNotFoundException {
       Wallet wallet =  repository.findById(deposit.getWalletId())
                            .orElseThrow(() ->  new WalletNotFoundException(
                               String.format("wallet with id %d not found",deposit.getWalletId())));

       wallet.setBalance(wallet.getBalance().add(deposit.getAmount()));
       repository.save(wallet);
       WalletDepositResponse response = new WalletDepositResponse();
       response.setStatus(SUCCESS.toString());
       response.setAmount(deposit.getAmount().toEngineeringString());
       return response;
    }

    @Override
    public CreateWalletResponse createNewWallet(CreateNewWallet request) {
        CreateWalletResponse response = new CreateWalletResponse();
        Wallet wallet = modelMapper.map(request,Wallet.class);
        wallet = repository.save(wallet);
        response.setWallet(wallet);
        response.setMessage("SUCCESSFUL");
        return response;
    }
}
