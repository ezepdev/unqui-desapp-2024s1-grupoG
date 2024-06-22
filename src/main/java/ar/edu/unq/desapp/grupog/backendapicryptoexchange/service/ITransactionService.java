package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.TradedVolume;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    Transaction startTransaction(StartTransactionRequest request);
    Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request);
    List<TradedVolume> getTransactionsByUserBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
