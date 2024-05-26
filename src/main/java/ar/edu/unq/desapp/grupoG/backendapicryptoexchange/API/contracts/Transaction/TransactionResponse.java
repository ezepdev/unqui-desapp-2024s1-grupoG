package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


public record TransactionResponse (
     Long transaction_id,
     String operation_type,
     String crypto_symbol,
     Double price,
     Integer amount,
     Integer final_price,
     Long creator_id,
     Long client_id,
     String state,
     LocalDateTime created_at,
     LocalDateTime updated_at
){}
