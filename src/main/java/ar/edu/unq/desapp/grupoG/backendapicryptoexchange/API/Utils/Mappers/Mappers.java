package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.Currency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.TransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.OperationType;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;

import java.time.LocalDateTime;
import java.util.List;

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
