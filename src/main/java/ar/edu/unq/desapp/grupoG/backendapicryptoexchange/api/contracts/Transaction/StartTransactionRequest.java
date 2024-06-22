package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction;


public record StartTransactionRequest (
    int transaction_intention_id,
    Long transaction_starter_user_id
){}


