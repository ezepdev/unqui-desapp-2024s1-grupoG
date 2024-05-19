package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;

public interface ITransactionService {
    Transaction executeTransactionOffer(int Id,TransactionIntentionRequest request);
}
