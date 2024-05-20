package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;

public interface ITransactionIntentionService {
    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request);
}
