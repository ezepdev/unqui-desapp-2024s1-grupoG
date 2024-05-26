package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

public record UpdateTransactionRequest (
     String action,
     Long user_id
){};
