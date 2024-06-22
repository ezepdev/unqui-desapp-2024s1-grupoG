package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class TransactionNotFound extends Error {
    public TransactionNotFound() {
        super.setMessage("Transaction not found");
        super.setCode("TRANSACTION_NOT_FOUND");
        super.setDescription("The transaction does not exist. Please check the transaction identifier");
    }
}
