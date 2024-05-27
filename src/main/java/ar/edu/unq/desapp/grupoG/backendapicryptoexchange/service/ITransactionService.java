package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    Transaction startTransaction(StartTransactionRequest request);
    Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request);
    List<Transaction> getTransactionsByUserBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
