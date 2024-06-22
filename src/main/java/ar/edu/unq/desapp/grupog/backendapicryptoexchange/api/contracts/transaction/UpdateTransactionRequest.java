package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction;

public record UpdateTransactionRequest (
     String action,
     Long user_id
){};
