package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StartTransactionResponse {
    private Integer created_transaction_id;
    @Builder.Default
    private String message = "Transaction executed successfully";
    private String URI;
}
