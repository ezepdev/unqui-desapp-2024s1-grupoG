package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;

import java.time.LocalDateTime;

public record TransactionIntentionResponse (
    Integer transaction_intention_id,
    String operation_type,
    String crypto_symbol,
    Double crypto_price,
    Integer crypto_amount,
    Currency final_price,
    Long creator_id,
    Double reputation,
    LocalDateTime creation_date,
    String state
){}
