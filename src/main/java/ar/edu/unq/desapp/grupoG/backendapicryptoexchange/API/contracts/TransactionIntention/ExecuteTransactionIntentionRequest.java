package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ExecuteTransactionIntentionRequest {

    @NotEmpty
    private int client_transaction_id;
}


