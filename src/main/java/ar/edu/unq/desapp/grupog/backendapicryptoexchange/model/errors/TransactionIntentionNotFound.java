package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class TransactionIntentionNotFound extends Error {
    public TransactionIntentionNotFound() {
        super("Transaction intention not found","The transaction intention does not exist. Please check the transaction intention id", "TRANSACTION_INTENTION_NOT_FOUND");
    }
}
