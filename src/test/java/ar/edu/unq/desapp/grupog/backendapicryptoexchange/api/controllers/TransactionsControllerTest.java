package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.StartTransactionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.TransactionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.TransactionsByUserRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers.TransactionMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private TransactionIntention transaction1;
    private TransactionIntention transaction2;
    private TradedVolume tradedVolume;
    private TradedVolume tradedVolume2;
    private User user;
    private User user2;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = User.builder().id(1L).email("user@email.com").build();
        user2 = User.builder().id(2L).email("user2@email.com").build();
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
        tradedVolume = TradedVolume.builder().symbol(CryptoCurrencySymbol.BTCUSDT).volume(100L).currentPrice(100.0).finalPrice(100L).build();
        tradedVolume2 = TradedVolume.builder().symbol(CryptoCurrencySymbol.AAVEUSDT).volume(200L).currentPrice(300.0).finalPrice(400L).build();
    }

    @Test
    void testGetTransactionsByUser() throws Exception {

        TransactionsByUserRequest request = new TransactionsByUserRequest(1L) ;// Ajusta según el constructor de tu request
        List<TradedVolume> tradedVolumes = List.of(tradedVolume, tradedVolume2);


        when(transactionService.getTransactionsByUserBetweenDates(1L, LocalDate.now().minusDays(1), LocalDate.now())).thenReturn(tradedVolumes);


        mockMvc.perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("fromDate", LocalDate.now().minusDays(1).toString())
                        .param("toDate", LocalDate.now().toString())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testStartTransaction() throws Exception {

        StartTransactionRequest request = new StartTransactionRequest(1,1L);

        StartTransactionResponse response = new StartTransactionResponse(1L, "Transaction started successfully", "/transactions/1");

        Transaction transaction = Transaction.builder().build();

        when(transactionService.startTransaction(request)).thenReturn(transaction);


        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(response.message())));
    }

    @Test
    void testUpdateTransactionStatus() throws Exception {

        int transactionId = 1;
        UpdateTransactionRequest request = new UpdateTransactionRequest(TransactionAction.CANCEL.name(),1L);
        Transaction transaction = Transaction.builder().id(1L).userOwner(user).userClient(user2).status(TransactionStatus.PENDING).createdAt(LocalDateTime.now()).intention(transaction1).build();
        TransactionResponse response = new TransactionResponse(1L, OperationType.VENTA.name(), CryptoCurrencySymbol.BTCUSDT.name(), 1.0, 10, 1L, 1L, 1L, TransactionStatus.CANCELED.name(), LocalDateTime.now(), LocalDateTime.now());


        when(transactionService.updateTransactionStatus(transactionId, request)).thenReturn(transaction);


        mockMvc.perform(patch("/transactions/" + transactionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.crypto_symbol", is(response.crypto_symbol()))); // Reemplaza 'someField' con el campo real de la respuesta
    }
}