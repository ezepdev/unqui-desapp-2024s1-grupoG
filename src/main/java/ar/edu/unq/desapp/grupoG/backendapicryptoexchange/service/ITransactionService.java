package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TradedVolume;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    Transaction startTransaction(StartTransactionRequest request);
    Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request);
    List<TradedVolume> getTransactionsByUserBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
