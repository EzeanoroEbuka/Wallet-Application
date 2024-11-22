package africa.semicolon.wollet.service;

import africa.semicolon.wollet.dto.response.CreateTransactionResponse;
import africa.semicolon.wollet.dto.request.CreateTransactionRequest;
import africa.semicolon.wollet.exception.TransactionNotFoundException;
import africa.semicolon.wollet.models.Transaction;

public interface TransactionService {

     CreateTransactionResponse createTransaction(CreateTransactionRequest transaction);

     Transaction getTransaction(Long transactionId) throws TransactionNotFoundException;
}

