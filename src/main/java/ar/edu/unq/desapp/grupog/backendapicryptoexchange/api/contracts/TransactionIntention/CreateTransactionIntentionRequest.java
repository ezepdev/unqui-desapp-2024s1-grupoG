package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.TransactionIntention;

public record CreateTransactionIntentionRequest (
    String operation_type,
    String crypto_symbol,
    Double crypto_price,
    Integer crypto_amount,
    Long intention_creator_id
){
    public Long finalPrice() {
        return (long) (crypto_price * crypto_amount);
    }
}
