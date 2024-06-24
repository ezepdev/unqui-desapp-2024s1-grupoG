package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


public class InvalidTransaction extends Error {

    public InvalidTransaction(String description) {
        super("Invalid transaction", description, "INVALID_TRANSACTION");
    }

}
