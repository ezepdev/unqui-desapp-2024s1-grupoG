package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TradedVolume;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ITransactionService {
    Transaction startTransaction(StartTransactionRequest request);
    Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request);
    List<TradedVolume> getTransactionsByUserBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
