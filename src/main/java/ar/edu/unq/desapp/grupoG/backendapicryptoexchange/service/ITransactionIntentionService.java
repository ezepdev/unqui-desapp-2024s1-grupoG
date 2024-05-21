package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.ExecuteTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;

import java.util.List;

public interface ITransactionIntentionService {
    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request);
    public List<TransactionIntention> getAllTransactionIntentions();

    Integer executeTransactionIntention(Integer id, ExecuteTransactionIntentionRequest request);
}
