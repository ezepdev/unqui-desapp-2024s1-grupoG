package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StartTransactionRequest {
    @NotEmpty
    private int transaction_intention_id;
    @NotEmpty
    private int client_transaction_id;
}


