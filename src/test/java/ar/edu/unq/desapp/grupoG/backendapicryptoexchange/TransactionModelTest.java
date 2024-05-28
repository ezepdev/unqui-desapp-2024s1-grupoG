package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionStatus;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.InvalidTransactionOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionModelTest {
    @Autowired
    static Transaction any_transaction;
    @Autowired
    static User any_user;
    @Autowired
    static User any_user2;

    @BeforeEach
    void setUp() {
        any_transaction = Transaction.builder().build();
        any_user = User.builder().build();
        any_user2 = User.builder().build();
    }

    @AfterEach
    void clean() {
        any_transaction = Transaction.builder().build();
        any_user = User.builder().build();
        any_user2 = User.builder().build();
    }

    @Test
    public void testConfirmTransfer() {
        any_transaction.confirmTransfer();
        assertEquals(TransactionStatus.TRANSFER_SUCCESS, any_transaction.getStatus());
    }

    @Test
    public void testConfirmReceipt() {
        any_transaction.confirmTransfer();
        any_transaction.confirmReceipt();
        assertEquals(TransactionStatus.SUCCESS, any_transaction.getStatus());
    }

    @Test
    public void testCancel() {
        any_transaction.cancel();
        assertEquals(TransactionStatus.CANCELED, any_transaction.getStatus());
    }

    @Test
    public void testIsUserImplicated() {
        any_user.setId(1L);
        any_user2.setId(2L);
        any_transaction.setUserOwner(any_user);
        boolean result = any_transaction.IsUserImplicated(any_user);
        assertTrue(any_transaction.IsUserImplicated(any_user));
        any_transaction.setUserClient(any_user2);
        assertTrue(any_transaction.IsUserImplicated(any_user2));
    }
}
