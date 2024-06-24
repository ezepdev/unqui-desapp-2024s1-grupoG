package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


public class TransactionNotFound extends Error {

    public TransactionNotFound() {
        super("Transaction not found","The transaction does not exist. Please check the transaction identifier", "TRANSACTION_NOT_FOUND");}
}
