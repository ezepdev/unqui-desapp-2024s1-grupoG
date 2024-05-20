package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionOffer;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTransactionIntentionRequest {
    private String operation_type;
    private String crypto_symbol;
    private Double crypto_price;
    private Double crypto_amount;
    private Integer creator_transaction_id;
}
