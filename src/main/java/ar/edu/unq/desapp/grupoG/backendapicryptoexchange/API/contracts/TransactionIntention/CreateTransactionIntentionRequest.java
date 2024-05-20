package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTransactionIntentionRequest {
    private String operation_type;
    private String crypto_symbol;
    private Double price;
    private Double amount;
    private Integer creator_transaction_id;
}
