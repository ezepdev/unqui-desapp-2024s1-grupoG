package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntentionState;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.PriceVariationMarginConflict;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionIntentionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

class TransactionIntentionServiceTest {
    @Autowired
    private ITransactionIntentionService transactionIntentionService;

    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private ICryptoService cryptoService;
    @MockBean
    private IExchangeService exchangeService;
    @MockBean
    private ITransactionIntentionRepository transactionIntentionRepository;



    @Test
    void testCreateTransactionIntention_WithValidData_ShouldCreateAndReturnTransactionIntention() {
        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);


        User user = User.builder().build();
        user.setId(3L);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(cryptoService.isAllowedPrice(any(), any())).thenReturn(true);
        when(exchangeService.convertToArs(any())).thenReturn(100L);
        when(transactionIntentionRepository.save(any(TransactionIntention.class)))
                .thenAnswer(i -> i.getArgument(0));

        var intention = transactionIntentionService.createTransactionIntention(request);

        assertNotNull(intention);
        assertEquals(request.operation_type(), intention.getType().name());
        assertEquals(request.crypto_symbol(), intention.getCryptoSymbol().name());
        assertEquals(request.crypto_price(), intention.getCryptoPrice());
        assertEquals(request.crypto_amount(), intention.getCryptoAmount());
        assertEquals(request.finalPrice(), intention.getFinalPrice());
        assertEquals(request.intention_creator_id(), intention.getCreator().getId());
        assertEquals(TransactionIntentionState.ACTIVE, intention.getState());
    }

    @Test
    void testCreateTransactionIntentionWithBadUser() {
        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);


        User user = User.builder().build();
        user.setId(2L);

        assertThrows(UserNotFound.class, () -> transactionIntentionService.createTransactionIntention(request));
    }
    @Test
    void testCreateTransactionIntentionWithBadPrice() {
        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);
        User user = User.builder().build();
        user.setId(3L);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(cryptoService.isAllowedPrice(any(CryptoCurrencySymbol.class), anyDouble())).thenReturn(false);

        assertThrows(PriceVariationMarginConflict.class, () -> transactionIntentionService.createTransactionIntention(request));
    }
    @Test
    void testGetActiveIntentions() {
        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);
        CreateTransactionIntentionRequest request2 = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);
        CreateTransactionIntentionRequest request3 = new CreateTransactionIntentionRequest("COMPRA", "ALICEUSDT", 10.0, 10, 3L);


        User user = User.builder().build();
        user.setId(3L);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(cryptoService.isAllowedPrice(any(), any())).thenReturn(true);
        when(exchangeService.convertToArs(any())).thenReturn(100L);
        when(transactionIntentionRepository.save(any(TransactionIntention.class)))
                .thenAnswer(i -> i.getArgument(0));

        var intention = transactionIntentionService.createTransactionIntention(request);
        var intention2 = transactionIntentionService.createTransactionIntention(request2);
        var intention3 = transactionIntentionService.createTransactionIntention(request3);
        List<TransactionIntention> finalIntentions = List.of(intention, intention2, intention3);
        when(transactionIntentionRepository.findByState(TransactionIntentionState.ACTIVE)).thenReturn(finalIntentions);
        var result = transactionIntentionService.getActiveIntentions();

        assertEquals(3, result.size());
        assertEquals(finalIntentions, result);
    }

}
