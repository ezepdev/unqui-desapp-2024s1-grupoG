package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction;

public record StartTransactionResponse (
    Long created_transaction_id,
    String message ,
    String Uri
){}
