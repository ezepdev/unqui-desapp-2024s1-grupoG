package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.OperationType;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;

import java.time.LocalDateTime;

public class TransactionMapper {
    public static TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getIntention().getType().name(),
                transaction.getIntention().getCryptoSymbol().name(),
                transaction.getIntention().getCryptoPrice(),
                transaction.getIntention().getCryptoAmount(),
                transaction.getIntention().getFinalPrice(),
                transaction.getUserOwner().getId(),
                transaction.getUserClient().getId(),
                transaction.getState().name(),
                transaction.getCreated_at(),
                LocalDateTime.now()
        );
    }
}
