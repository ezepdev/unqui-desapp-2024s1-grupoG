package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.Utils.mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Transaction.TransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;

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
                transaction.getStatus().name(),
                transaction.getCreated_at(),
                LocalDateTime.now()
        );
    }
}
