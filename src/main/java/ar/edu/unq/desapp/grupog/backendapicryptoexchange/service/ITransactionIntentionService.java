package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntention;

import java.util.List;

public interface ITransactionIntentionService {
    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request);
    public List<TransactionIntention> getActiveIntentions();
}
