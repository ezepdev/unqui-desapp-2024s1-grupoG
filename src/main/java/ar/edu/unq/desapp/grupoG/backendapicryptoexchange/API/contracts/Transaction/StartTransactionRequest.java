package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


public record StartTransactionRequest (
    int transaction_intention_id,
    Long transaction_starter_user_id
){}


