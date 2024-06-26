package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.Currency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers.TransactionIntentionMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionIntentionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionIntentionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITransactionIntentionService transactionService;
    @MockBean
    private TransactionIntentionMapper transactionIntentionMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private TransactionIntention transaction1;
    private TransactionIntention transaction2;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        User user = User.builder().id(1L).email("user@email.com").build();
        User user2 = User.builder().id(2L).email("user2@email.com").build();
        transaction1 = TransactionIntention.builder().
                creationDate(LocalDateTime.now()).
                cryptoAmount(100).
                cryptoPrice(100.0).
                cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).
                creator(user).
                finalPrice(100L).
                id(1L).
                state(TransactionIntentionState.ACTIVE).
                type(OperationType.COMPRA).
                build();
        transaction2 = TransactionIntention.builder().
                creationDate(LocalDateTime.now()).
                cryptoAmount(100).
                cryptoPrice(100.0).
                cryptoSymbol(CryptoCurrencySymbol.BTCUSDT).
                creator(user2).
                finalPrice(100L).
                id(1L).
                state(TransactionIntentionState.ACTIVE).
                type(OperationType.COMPRA).
                build();
    }

    @Test
    void testGetActiveTransactionIntentions() throws Exception {


        List<TransactionIntention> transactionIntentions = List.of(transaction1, transaction2);


        when(transactionService.getActiveIntentions()).thenReturn(transactionIntentions);


        mockMvc.perform(get("/transaction_intentions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateTransactionIntention() throws Exception {

        CreateTransactionIntentionRequest request = new CreateTransactionIntentionRequest(OperationType.COMPRA.name(),CryptoCurrencySymbol.BTCUSDT.name(),100.0,100 , 1L);
        CreateTransactionIntentionResponse response = new CreateTransactionIntentionResponse(1L,OperationType.COMPRA.name(),CryptoCurrencySymbol.BTCUSDT.name(),100.0,100, new Currency(100L , CryptoCurrencySymbol.BTCUSDT.name()),1L, LocalDateTime.now(), TransactionIntentionState.ACTIVE.name());


        when(transactionService.createTransactionIntention(request)).thenReturn(transaction1);



        mockMvc.perform(post("/transaction_intentions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.crypto_price", is(response.crypto_price()))); // Reemplaza 'someField' con el campo real de la respuesta
    }
}
