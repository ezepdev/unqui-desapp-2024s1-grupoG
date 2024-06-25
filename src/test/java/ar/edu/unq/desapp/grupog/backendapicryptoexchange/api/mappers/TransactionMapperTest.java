package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.TransactionIntentionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMapperTest {

    @Test
    void mapToTransactionIntentionResponse() {
        TransactionIntention intention = TransactionIntention.builder().type(OperationType.COMPRA).cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).cryptoPrice(10.0).cryptoAmount(1).finalPrice(100L).creator(User.builder().id(1L).build()).creationDate(LocalDateTime.now()).build();

        TransactionIntentionResponse transactionResponse = TransactionIntentionMapper.mapToTransactionIntentionResponse(intention);
        assertEquals(intention.getId(), transactionResponse.transaction_intention_id());
        assertEquals(intention.getType().name(), transactionResponse.operation_type());
        assertEquals(intention.getCryptoSymbol().name(), transactionResponse.crypto_symbol());
        assertEquals(intention.getCryptoPrice(), transactionResponse.crypto_price());
        assertEquals(intention.getCryptoAmount(), transactionResponse.crypto_amount());
        assertEquals(intention.getFinalPrice(), transactionResponse.final_price().price());
        assertEquals(intention.getCreator().getId(), transactionResponse.creator_id());
        assertEquals(intention.getCreator().getReputation(), transactionResponse.reputation());
        assertEquals(intention.getCreationDate(), transactionResponse.creation_date());
        assertEquals(intention.getState().name(), transactionResponse.state());
    }

    @Test
    void mapToTransactionIntentionsResponse() {
        TransactionIntention intention = TransactionIntention.builder().type(OperationType.COMPRA).cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).cryptoPrice(10.0).cryptoAmount(1).finalPrice(100L).creator(User.builder().id(1L).build()).creationDate(LocalDateTime.now()).build();
        TransactionIntention intention2 = TransactionIntention.builder().type(OperationType.VENTA).cryptoSymbol(CryptoCurrencySymbol.AAVEUSDT).cryptoPrice(30.0).cryptoAmount(2).finalPrice(200L).creator(User.builder().id(1L).build()).creationDate(LocalDateTime.now()).build();

        List<TransactionIntentionResponse> transactionResponses = TransactionIntentionMapper.mapToTransactionIntentionResponses(List.of(intention, intention2));

        assertEquals(intention.getId(), transactionResponses.get(0).transaction_intention_id());
        assertEquals(intention.getType().name(), transactionResponses.get(0).operation_type());
        assertEquals(intention.getCryptoSymbol().name(), transactionResponses.get(0).crypto_symbol());
        assertEquals(intention.getCryptoPrice(), transactionResponses.get(0).crypto_price());
        assertEquals(intention.getCryptoAmount(), transactionResponses.get(0).crypto_amount());
        assertEquals(intention.getFinalPrice(), transactionResponses.get(0).final_price().price());
        assertEquals(intention.getCreator().getId(), transactionResponses.get(0).creator_id());
        assertEquals(intention.getCreator().getReputation(), transactionResponses.get(0).reputation());
        assertEquals(intention.getCreationDate(), transactionResponses.get(0).creation_date());
        assertEquals(intention.getState().name(), transactionResponses.get(0).state());

        assertEquals(intention2.getId(), transactionResponses.get(1).transaction_intention_id());
        assertEquals(intention2.getType().name(), transactionResponses.get(1).operation_type());
        assertEquals(intention2.getCryptoSymbol().name(), transactionResponses.get(1).crypto_symbol());
        assertEquals(intention2.getCryptoPrice(), transactionResponses.get(1).crypto_price());
        assertEquals(intention2.getCryptoAmount(), transactionResponses.get(1).crypto_amount());
        assertEquals(intention2.getFinalPrice(), transactionResponses.get(1).final_price().price());
        assertEquals(intention2.getCreator().getId(), transactionResponses.get(1).creator_id());
        assertEquals(intention2.getCreator().getReputation(), transactionResponses.get(1).reputation());
        assertEquals(intention2.getCreationDate(), transactionResponses.get(1).creation_date());
        assertEquals(intention2.getState().name(), transactionResponses.get(1).state());
    }

    @Test
    void mapToCreateTransactionIntentionResponse() {
        TransactionIntention intention = TransactionIntention.builder().type(OperationType.COMPRA).cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).cryptoPrice(10.0).cryptoAmount(1).finalPrice(100L).creator(User.builder().id(1L).build()).creationDate(LocalDateTime.now()).build();

        CreateTransactionIntentionResponse transactionResponse = TransactionIntentionMapper.mapToCreateTransactionIntentionResponse(intention);

        assertEquals(intention.getId(), transactionResponse.transaction_intention_id());
        assertEquals(intention.getType().name(), transactionResponse.operation_type());
        assertEquals(intention.getCryptoSymbol().name(), transactionResponse.crypto_symbol());
        assertEquals(intention.getCryptoPrice(), transactionResponse.crypto_price());
        assertEquals(intention.getCryptoAmount(), transactionResponse.crypto_amount());
        assertEquals(intention.getFinalPrice(), transactionResponse.final_price().price());
        assertEquals(intention.getCreator().getId(), transactionResponse.creator_id());
        assertEquals(intention.getCreationDate(), transactionResponse.creation_date());
        assertEquals(intention.getState().name(), transactionResponse.state());
    }

    @Test
    void mapCreateTransactionIntentionRequestToTransactionIntention() {
        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 1L);
        TransactionIntention intention = TransactionIntentionMapper.mapToTransactionIntention(request);

        assertEquals(request.operation_type(), intention.getType().name());
        assertEquals(request.crypto_symbol(), intention.getCryptoSymbol().name());
        assertEquals(request.crypto_price(), intention.getCryptoPrice());
        assertEquals(request.crypto_amount(), intention.getCryptoAmount());
    }


}
