package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class TransactionOfferNotFound extends Error {
    public String getMessage(){
        return "Transaction offer not found";
    }
    public String getDescription(){
        return "The transaction offer does not exist. Please check the transaction offer id";
    }
    public String getCode(){
        return "TRANSACTION_OFFER_NOT_FOUND";
    }
}
