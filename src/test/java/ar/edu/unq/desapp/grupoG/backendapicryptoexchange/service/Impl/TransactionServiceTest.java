package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IExchangeService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private ITransactionRepository transactionRepository;

    @MockBean
    private IExchangeService exchangeService;

    @MockBean
    private ITransactionIntentionRepository transactionIntentionRepository;

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private ICryptoService cryptoService;
    @Test

    public void testStartTransaction_WithValidData_ShouldReturnTransaction() {
        StartTransactionRequest request = new StartTransactionRequest(1, 2L);
        TransactionIntention intention = TransactionIntention.builder().build();
        User owner = User.builder().build();
        User client = User.builder().build();
        owner.setId(1L);
        client.setId(2L);
        intention.setCreator(owner);
        when(transactionIntentionRepository.findById(any())).thenReturn(Optional.of(intention));

        when(userRepository.findById(any())).thenReturn(Optional.of(owner), Optional.of(client));
        when(transactionRepository.save(any())).thenReturn(new Transaction());

        Transaction result = transactionService.startTransaction(request);

        assertNotNull(result);
    }

    @Test
    public void testUpdateTransactionStatus_WithValidData_ShouldReturnUpdatedTransaction() {
        Integer transactionId = 1;
        UpdateTransactionRequest request = new UpdateTransactionRequest("CONFIRM_TRANSFER", 1L);
        Transaction transaction = Transaction.builder().build();
        TransactionIntention intention = TransactionIntention.builder().build();
        User user = User.builder().build();
        user.setId(1L);
        transaction.setUserOwner(user);
        transaction.setIntention(intention);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(cryptoService.isAllowedPrice(any(), any())).thenReturn(true);
        when(transactionRepository.save(any())).thenReturn(transaction);

        // Act
        Transaction result = transactionService.updateTransactionStatus(transactionId, request);

        // Assert
        assertNotNull(result);
        // Add more assertions as needed
    }

//    @Test
//    public void testGetTransactionsByUserBetweenDates_WithValidData_ShouldReturnListOfTransactions() {
//        // Arrange
//        Long userId = 1L;
//        LocalDate fromDate = LocalDate.now().minusDays(7);
//        LocalDate toDate = LocalDate.now();
//
//        // Mock data for repository method call
//        List<TradedVolume> tradedVolumes = List.of();
//        when(transactionRepository.tradedVolumeCryptosBetweenDates(userId, fromDate, toDate)).thenReturn(tradedVolumes);
//
//
//        // Act
//        List<TradedVolume> result = transactionService.getTransactionsByUserBetweenDates(userId, fromDate, toDate);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(0, result.size());
//    }

}