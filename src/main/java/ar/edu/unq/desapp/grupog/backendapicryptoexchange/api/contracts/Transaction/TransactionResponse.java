package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Transaction;

import java.time.LocalDateTime;


public record TransactionResponse (
     Long transaction_id,
     String operation_type,
     String crypto_symbol,
     Double price,
     Integer amount,
     Long final_price,
     Long creator_id,
     Long client_id,
     String state,
     LocalDateTime created_at,
     LocalDateTime updated_at
){}
