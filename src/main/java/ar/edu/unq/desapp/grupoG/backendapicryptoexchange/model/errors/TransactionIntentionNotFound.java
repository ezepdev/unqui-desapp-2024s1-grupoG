package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;


public class TransactionIntentionNotFound extends Error {
    public final String getMessage(){
        return "Transaction intention not found";
    }
    public final String getDescription(){
        return "The transaction offer does not exist. Please check the transaction intention id";
    }
    public final String getCode(){
        return "TRANSACTION_INTENTION_NOT_FOUND";
    }
}
