package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {
    private Integer transaction_id;
    private String operation_type;
    private String crypto_symbol;
    private Double price;
    private Double amount;
    private Double final_price;
    private Integer creator_id;
    private Integer client_id;
    private String state;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
