package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention;


import java.time.LocalDateTime;

public record CreateTransactionIntentionResponse (
    Long transaction_intention_id,
    String operation_type,
    String crypto_symbol,
    Double crypto_price,
    Integer crypto_amount,
    Currency final_price,
    Long creator_id,
    LocalDateTime creation_date,
    String state
){}




