package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateTransactionIntentionResponse {
    private Integer transaction_intention_id;
    private String operation_type;
    private String crypto_symbol;
    private Double price;
    private Double amount;
    private Double final_price;
    private Integer creator_id;
}


