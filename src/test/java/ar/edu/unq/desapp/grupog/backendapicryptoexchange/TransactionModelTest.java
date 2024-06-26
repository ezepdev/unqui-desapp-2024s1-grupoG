package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionStatus;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.InvalidTransactionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionModelTest {
    @Autowired
    static Transaction any_transaction;
    @Autowired
    static User any_user;
    @Autowired
    static User any_user2;
    @Autowired
    static User any_user3;

    @BeforeEach
    void setUp() {
        any_transaction = Transaction.builder().build();
        any_user = User.builder().build();
        any_user2 = User.builder().build();
        any_user3 = User.builder().build();
    }

    @Test
    void testConfirmTransfer() {
        any_transaction.confirmTransfer();
        assertEquals(TransactionStatus.TRANSFER_SUCCESS, any_transaction.getStatus());
    }

    @Test
    void testConfirmReceipt() {
        any_transaction.confirmTransfer();
        any_transaction.confirmReceipt();
        assertEquals(TransactionStatus.SUCCESS, any_transaction.getStatus());
    }
    @Test
    void testConfirmReceiptWithNotPendingStatus() {
        any_transaction.setStatus(TransactionStatus.SUCCESS);
        assertThrows(InvalidTransactionOperation.class, () -> {
            any_transaction.confirmReceipt();
        });
    }
    @Test
    void testConfirmTransferWithNotPendingStatus() {
        any_transaction.setStatus(TransactionStatus.SUCCESS);
        assertThrows(InvalidTransactionOperation.class, () -> {
            any_transaction.confirmTransfer();
        });
    }

    @Test
    void testCancel() {
        any_transaction.cancel();
        assertEquals(TransactionStatus.CANCELED, any_transaction.getStatus());
    }

    @Test
    void testSuccessTransactionCantBeCanceled() {
        any_transaction.setStatus(TransactionStatus.SUCCESS);

        assertThrows(InvalidTransactionOperation.class, () -> {
            any_transaction.cancel();
        });
    }

    @Test
    void testIsUserImplicated() {
        any_user.setId(1L);
        any_user2.setId(2L);
        any_user3.setId(3L);
        any_transaction.setUserOwner(any_user);
        assertTrue(any_transaction.isUserImplicated(any_user));
        any_transaction.setUserClient(any_user2);
        assertTrue(any_transaction.isUserImplicated(any_user2));
        assertFalse(any_transaction.isUserImplicated(any_user3));
    }
}
