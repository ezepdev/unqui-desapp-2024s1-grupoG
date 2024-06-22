package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction;


public record StartTransactionRequest (
    int transactionIntentionId,
    Long transactionStarterUserId
){}


