package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
public class TransactionIntentionResponse {
    private Integer transaction_intention_id;
    private String operation_type;
    private String crypto_symbol;
    private Double price;
    private Double amount;
    private Currency final_price;
    private Integer creator_id;
    private Double reputation;
    private LocalDateTime creation_date;
}
