package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.utils.mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.TransactionIntention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.TransactionIntention.Currency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.TransactionIntention.TransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.OperationType;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;

import java.time.LocalDateTime;

public class Mappers {

    public static TransactionIntention mapToTransactionIntention(CreateTransactionIntentionRequest request) {
        return TransactionIntention.builder()
                .type(OperationType.valueOf(request.operation_type()))
                .creationDate(LocalDateTime.now())
                .cryptoSymbol(CryptoCurrencySymbol.valueOf((request.crypto_symbol())))
                .cryptoPrice(request.crypto_price())
                .cryptoAmount(request.crypto_amount())
                .build();
    }

    public static TransactionIntentionResponse mapToTransactionIntentionResponse(TransactionIntention transactionIntention) {
        return new TransactionIntentionResponse(
                transactionIntention.getId(),
                transactionIntention.getType().name(),
                transactionIntention.getCryptoSymbol().name(),
                transactionIntention.getCryptoPrice(),
                transactionIntention.getCryptoAmount(),
                new Currency( transactionIntention.getFinalPrice(), "ARS" ),
                transactionIntention.getCreator().getId(),
                transactionIntention.getCreator().get_reputation(),
                transactionIntention.getCreationDate(),
                transactionIntention.getState().name()
        );
    }

    public static CreateTransactionIntentionResponse mapToCreateTransactionIntentionResponse(TransactionIntention transactionIntention) {
        return new CreateTransactionIntentionResponse(
                transactionIntention.getId(),
                transactionIntention.getType().name(),
                transactionIntention.getCryptoSymbol().name(),
                transactionIntention.getCryptoPrice(),
                transactionIntention.getCryptoAmount(),
                new Currency( transactionIntention.getFinalPrice(), "ARS" ),
                transactionIntention.getCreator().getId(),
                transactionIntention.getCreationDate(),
                transactionIntention.getState().name()
        );
    }
}
