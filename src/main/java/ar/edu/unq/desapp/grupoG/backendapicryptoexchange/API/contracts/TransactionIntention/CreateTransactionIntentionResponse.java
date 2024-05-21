package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CreateTransactionIntentionResponse {
    private Integer transaction_intention_id;
    private String operation_type;
    private String crypto_symbol;
    private Double price;
    private Double amount;
    private Currency final_price;
    private Integer creator_id;
    private LocalDateTime creation_date;
    private String transaction_intention_state;
}




