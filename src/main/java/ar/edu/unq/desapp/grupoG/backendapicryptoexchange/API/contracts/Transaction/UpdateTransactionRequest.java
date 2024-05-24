package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTransactionRequest {
    @NotBlank
    @NotNull
    private String action;
    private Long user_id;
}
