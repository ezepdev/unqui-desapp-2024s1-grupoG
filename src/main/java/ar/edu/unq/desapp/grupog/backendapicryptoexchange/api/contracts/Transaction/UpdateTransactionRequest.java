package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Transaction;

public record UpdateTransactionRequest (
     String action,
     Long user_id
){};
