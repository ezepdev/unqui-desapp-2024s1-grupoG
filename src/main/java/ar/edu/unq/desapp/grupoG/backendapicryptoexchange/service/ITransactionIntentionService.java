package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionOffer.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;

public interface ITransactionIntentionService {
    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request);
}
