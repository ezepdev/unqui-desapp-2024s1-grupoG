package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction;

public record StartTransactionResponse (
    Long created_transaction_id,
    String message ,
    String Uri
){}
