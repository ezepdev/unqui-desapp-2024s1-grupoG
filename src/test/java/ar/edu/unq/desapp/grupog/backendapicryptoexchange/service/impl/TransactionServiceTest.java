package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ITransactionRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import org.hibernate.sql.Update;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        when(userRepository.findById(any())).thenReturn(Optional.of(owner));
        when(userRepository.findById(any())).thenReturn(Optional.of(client));
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

    @Test
    public void testGetTransactionsByUserBetweenDates() {
        TradedVolume tradedVolume = TradedVolume.builder().symbol(CryptoCurrencySymbol.AAVEUSDT).volume(1L).currentPrice(10.0).finalPrice(1L).build();
        TradedVolume tradedVolume2 = TradedVolume.builder().symbol(CryptoCurrencySymbol.AAVEUSDT).volume(1L).currentPrice(20.0).finalPrice(1L).build();
        TradedVolume tradedVolume3 = TradedVolume.builder().symbol(CryptoCurrencySymbol.AAVEUSDT).volume(1L).currentPrice(29.0).finalPrice(1L).build();
        CryptoCurrency cryptoCurrency = CryptoCurrency.builder().price(20.0).build();
        List<TradedVolume> tradedVolumesFinal = List.of(tradedVolume, tradedVolume2, tradedVolume3);

        when(transactionRepository.tradedVolumeCryptosBetweenDates(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(tradedVolumesFinal);
        when(cryptoService.getCurrencyBySymbol(any())).thenReturn(cryptoCurrency);
        when(exchangeService.convertToArs(anyDouble())).thenReturn(1L);
        List<TradedVolume> tradedVolumes = transactionService.getTransactionsByUserBetweenDates(1L, LocalDate.now(), LocalDate.now());

        assertNotNull(tradedVolumes);
        assertEquals(3, tradedVolumes.size());

    }
    @Test
    public void testUpdateTransactionStatusNotFound() {
        UpdateTransactionRequest request = new UpdateTransactionRequest("CONFIRM_TRANSFER", 1L);
        when(transactionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFound.class, () -> {
            transactionService.updateTransactionStatus(1, request);
        });

        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateTransactionStatusWithInvalidUser() {
        Integer transactionId = 1;
        UpdateTransactionRequest request = new UpdateTransactionRequest("CONFIRM_TRANSFER", 1L);
        Transaction transaction = Transaction.builder().build();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class, () -> {
            transactionService.updateTransactionStatus(1, request);
        });

        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateTransactionStatusWithUserNotImplicated() {
        Integer transactionId = 1;
        UpdateTransactionRequest request = new UpdateTransactionRequest("CONFIRM_TRANSFER", 2L);
        Transaction transaction = Transaction.builder().build();
        TransactionIntention intention = TransactionIntention.builder().build();
        User user = User.builder().build();
        User user2 = User.builder().build();
        User user3 = User.builder().build();
        user.setId(1L);
        user2.setId(2L);
        user3.setId(3L);
        transaction.setUserOwner(user);
        transaction.setUserClient(user3);
        transaction.setIntention(intention);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));

        assertThrows(UserNotAuthorized.class, () -> {
            transactionService.updateTransactionStatus(1, request);
        });

        verify(transactionRepository, times(1)).findById(1);
    }
    @Test
    public void testUpdateTransactionStatusWithInvalidPrice() {
        Integer transactionId = 1;
        UpdateTransactionRequest request = new UpdateTransactionRequest("CONFIRM_TRANSFER", 1L);
        Transaction transaction = Transaction.builder().build();
        TransactionIntention intention = TransactionIntention.builder().build();
        User user = User.builder().build();
        User user3 = User.builder().build();
        user.setId(1L);
        user3.setId(3L);
        transaction.setUserOwner(user);
        transaction.setUserClient(user3);
        transaction.setIntention(intention);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cryptoService.isAllowedPrice(any(CryptoCurrencySymbol.class), anyDouble())).thenReturn(false);
        assertThrows(PriceVariationMarginConflict.class, () -> {
            transactionService.updateTransactionStatus(1, request);
        });

        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    public void testStartUserCantBeTheOwner() {
        StartTransactionRequest request = new StartTransactionRequest(1, 2L);
        TransactionIntention intention = TransactionIntention.builder().build();
        User owner = User.builder().build();
        User client = User.builder().build();
        owner.setId(2L);
        intention.setCreator(owner);
        when(transactionIntentionRepository.findById(any())).thenReturn(Optional.of(intention));

        when(userRepository.findById(any())).thenReturn(Optional.of(owner));
        when(transactionRepository.save(any())).thenReturn(new Transaction());

        assertThrows(InvalidTransaction.class, () -> {
            transactionService.startTransaction(request);
        });
    }

    @Test
    public void testTransactionIntentionNotFound() {
        StartTransactionRequest request = new StartTransactionRequest(1, 2L);
        TransactionIntention intention = TransactionIntention.builder().build();
        User owner = User.builder().build();
        User client = User.builder().build();
        owner.setId(2L);
        intention.setCreator(owner);

        when(userRepository.findById(any())).thenReturn(Optional.of(owner));
        when(transactionRepository.save(any())).thenReturn(new Transaction());

        assertThrows(TransactionIntentionNotFound.class, () -> {
            transactionService.startTransaction(request);
        });
    }

    @Test
    public void testUserOfTransactionIntentionNotFound() {
        StartTransactionRequest request = new StartTransactionRequest(1, 2L);
        TransactionIntention intention = TransactionIntention.builder().build();
        User owner = User.builder().build();
        owner.setId(2L);
        intention.setCreator(owner);
        when(transactionIntentionRepository.findById(any())).thenReturn(Optional.of(intention));

        when(transactionRepository.save(any())).thenReturn(new Transaction());

        assertThrows(UserNotFound.class, () -> {
            transactionService.startTransaction(request);
        });
    }
}