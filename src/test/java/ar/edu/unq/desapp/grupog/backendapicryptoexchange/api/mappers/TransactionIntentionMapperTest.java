package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.TransactionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionIntentionMapperTest {

    @Test
    void mapToTransactionIntentionResponse() {
        Transaction transaction = Transaction.builder()
                .id(1L)
                .intention(TransactionIntention.builder().type(OperationType.COMPRA).cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).cryptoPrice(10.0).cryptoAmount(1).finalPrice(100L).creator(User.builder().id(1L).build()).creationDate(LocalDateTime.now()).build())
                .userOwner(User.builder().id(1L).build())
                .userClient(User.builder().id(2L).build())

                .status(TransactionStatus.SUCCESS)
                .build();
        TransactionResponse transactionResponse = TransactionMapper.mapToTransactionResponse(transaction);
        assertEquals(transaction.getId(), transactionResponse.transaction_id());
    }
}
