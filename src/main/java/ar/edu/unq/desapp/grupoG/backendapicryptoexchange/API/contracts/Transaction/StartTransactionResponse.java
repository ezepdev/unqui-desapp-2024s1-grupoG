package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

public record StartTransactionResponse (
    Long created_transaction_id,
    String message ,
    String Uri
){}
