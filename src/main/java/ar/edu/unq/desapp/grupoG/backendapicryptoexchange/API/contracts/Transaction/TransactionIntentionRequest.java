package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TransactionIntentionRequest {

    @NotEmpty
    private int clientTransactionId;
}


