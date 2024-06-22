package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntentionState;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionIntentionServiceTest {
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
    public void testCreateTransactionIntention_WithValidData_ShouldCreateAndReturnTransactionIntention() {
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



}
