package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;

public record CreateTransactionIntentionRequest (
    String operation_type,
    String crypto_symbol,
    Double crypto_price,
    Integer crypto_amount,
    Long intention_creator_id
){
    public Double finalPrice() {
        return crypto_price * crypto_amount;
    }
}
