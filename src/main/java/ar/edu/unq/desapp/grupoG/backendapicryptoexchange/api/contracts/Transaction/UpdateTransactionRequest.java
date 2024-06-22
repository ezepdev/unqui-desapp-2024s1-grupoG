package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction;

public record UpdateTransactionRequest (
     String action,
     Long user_id
){};
