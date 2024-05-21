package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.ExecuteTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;

public interface ITransactionService {
    Transaction executeTransactionOffer(Integer Id, ExecuteTransactionIntentionRequest request);
}
