package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.request.CreateTransactionRequest;
import africa.semicolon.wollet.dto.response.CreateTransactionResponse;
import africa.semicolon.wollet.exception.TransactionNotFoundException;
import africa.semicolon.wollet.models.Transaction;
import africa.semicolon.wollet.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImp implements TransactionService{

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;
    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest transactionRequest) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction = transactionRepository.save(transaction);
        return modelMapper.map(transaction, CreateTransactionResponse.class);
    }

    @Override
    public Transaction getTransaction(Long transactionId) throws TransactionNotFoundException {
        return transactionRepository.findById(transactionId)
                .orElseThrow( () -> new TransactionNotFoundException(
                        String.format("Transaction with this id %d not found",transactionId)));
    }

}
