package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.TransactionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.Transaction;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
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
                transaction.getCreatedAt(),
                LocalDateTime.now()
        );
    }
}
