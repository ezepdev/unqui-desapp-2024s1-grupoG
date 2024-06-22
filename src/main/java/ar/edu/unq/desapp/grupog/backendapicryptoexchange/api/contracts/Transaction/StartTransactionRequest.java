package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Transaction;


public record StartTransactionRequest (
    int transaction_intention_id,
    Long transaction_starter_user_id
){}


