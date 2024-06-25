package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.UpdateActionNotAllowed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserModelTest {
    @Autowired
    static User client;
    @Autowired
    static User owner;
    @MockBean
    private Transaction transaction;
    @MockBean
    private TransactionIntention intention;
    @BeforeAll
    static void setUp() {
        client = User.builder().build();
        owner = User.builder().build();
        client.setId(1L);
        owner.setId(2L);
    }

    @AfterEach
    void clean() {
        client = User.builder().build();
        owner = User.builder().build();
        client.setId(1L);
        owner.setId(2L);
    }

    @Test
    void testAnyUserCanAddReputationPoint() {
        Integer points = 30;
        Integer started_points = client.getReputationPoints();
        client.setOperationsAmount(client.getOperationsAmount() + 1);
        client.addPoints(points);
        assertEquals(started_points + points, client.getReputationPoints());
        client.setReputationPoints(started_points);
        assertEquals(0, client.getReputationPoints());
        assertEquals(1, client.getOperationsAmount());
    }
    @Test
    void testUpdateReputation() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        when(transaction.getCreatedAt()).thenReturn(now);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);

        Method method = User.class.getDeclaredMethod("updateReputation", Transaction.class);
        method.setAccessible(true);

        method.invoke(client, transaction);

        assertEquals(10, client.getReputationPoints());
        assertEquals(10, owner.getReputationPoints());
        assertEquals(1, client.getOperationsAmount());
        assertEquals(1, owner.getOperationsAmount());
    }
    @Test
    void testUpdateReputationWithMoreThan30Minutes() throws Exception {
        LocalDateTime time = LocalDateTime.now().minusMinutes(35);
        when(transaction.getCreatedAt()).thenReturn(time);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);

        Method method = User.class.getDeclaredMethod("updateReputation", Transaction.class);
        method.setAccessible(true);

        method.invoke(client, transaction);

        assertEquals(5, client.getReputationPoints());
        assertEquals(5, owner.getReputationPoints());
        assertEquals(1, client.getOperationsAmount());
        assertEquals(1, owner.getOperationsAmount());
    }

    @Test
    void testAnyUserCanBeBuild() {
        User user = User.builder().build();
        user.setName("Jose");
        user.setEmail("JOSE.EMAIL@GMAIL.COM");
        user.setLastname("AMALFITANI");
        user.setWalletAddress("12345678");
        user.setAddress("ADDRESS");
        user.setPassword("Pepe1234!");
        user.setCvu("123456789123456789123");
        user.setId(1L);
        assertEquals("Jose", user.getName());
        assertEquals("JOSE.EMAIL@GMAIL.COM", user.getEmail());
        assertEquals("AMALFITANI", user.getLastname());
        assertEquals("12345678", user.getWalletAddress());
        assertEquals("ADDRESS", user.getAddress());
        assertEquals("Pepe1234!", user.getPassword());
        assertEquals("123456789123456789123", user.getCvu());
        assertEquals("JOSE.EMAIL@GMAIL.COM", user.getUsername());
        assertEquals(1L, user.getId());
    }

    @Test
    void testExecute_ConfirmTransferActionAsBuyer_ShouldCallConfirmTransfer() {
        client.setId(1L);
        owner.setId(2L);
        TransactionIntention sold_intention = TransactionIntention.builder().type(OperationType.VENTA).build();
        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);

        client.execute(TransactionAction.CONFIRM_TRANSFER, transaction);

        verify(transaction, times(1)).confirmTransfer();
    }

    @Test
    void testExecute_ConfirmTransferActionAsSeller_ShouldThrowUpdateActionNotAllowedException() {
        client.setId(1L);
        owner.setId(2L);
        TransactionIntention sold_intention = TransactionIntention.builder().type(OperationType.COMPRA).build();


        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);

        assertThrows(UpdateActionNotAllowed.class, () -> client.execute(TransactionAction.CONFIRM_TRANSFER, transaction));
    }
    @Test
    void testExecute_ConfirmReceiptActionAsSeller_ShouldCallConfirmReceipt() {
        client.setId(1L);
        owner.setId(2L);
        TransactionIntention sold_intention = TransactionIntention.builder().creationDate(LocalDateTime.now()).type(OperationType.VENTA).build();
        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);
        when(transaction.getCreatedAt()).thenReturn(LocalDateTime.now());

        owner.execute(TransactionAction.CONFIRM_RECEIPT, transaction);

        verify(transaction, times(1)).confirmReceipt();
    }
    @Test
    void testExecute_Default() {
        client.setId(1L);
        owner.setId(2L);
        TransactionIntention sold_intention = TransactionIntention.builder().creationDate(LocalDateTime.now()).type(OperationType.VENTA).build();
        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);
        when(transaction.getCreatedAt()).thenReturn(LocalDateTime.now());

        assertThrows(UpdateActionNotAllowed.class, () -> owner.execute(TransactionAction.INVALID_ACTION, transaction));
    }

    @Test
    void testExecute_ConfirmReceiptActionAsBuyer_ShouldThrowUpdateActionNotAllowedException() {
        client.setId(1L);
        owner.setId(2L);
        TransactionIntention sold_intention = TransactionIntention.builder().type(OperationType.COMPRA).build();


        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);
        when(transaction.getCreatedAt()).thenReturn(LocalDateTime.now());

        assertThrows(UpdateActionNotAllowed.class, () -> owner.execute(TransactionAction.CONFIRM_RECEIPT, transaction));
    }


    @Test
    void testAddPoints() {
        client.addPoints(10);
        assertEquals(10, client.getReputationPoints());
    }

    @Test
    void testAddOperation() {
        client.addOperation();
        assertEquals(1, client.getOperationsAmount());
    }

    @Test
    void testGetReputation() {
        assertEquals(0, client.getReputation());
        client.addPoints(10);
        client.addOperation();
        assertEquals(10.0, client.getReputation());

        client.addPoints(5);
        client.addOperation();
        assertEquals(7.5, client.getReputation());
    }

    @Test
    void testExecuteConfirmReceiptAsSeller() {
        TransactionIntention sold_intention = TransactionIntention.builder().type(OperationType.COMPRA).build();
        when(transaction.getIntention()).thenReturn(sold_intention);
        when(transaction.getCreatedAt()).thenReturn(LocalDateTime.now());
        when(transaction.getUserClient()).thenReturn(client);
        when(transaction.getUserOwner()).thenReturn(owner);

        owner.execute(TransactionAction.CONFIRM_TRANSFER, transaction);
        client.execute(TransactionAction.CONFIRM_RECEIPT, transaction);

        verify(transaction, times(1)).confirmReceipt();
    }


    @Test
    void testExecuteCancel() {
        client.execute(TransactionAction.CANCEL, transaction);

        verify(transaction, times(1)).cancel();
        assertEquals(-20, client.getReputationPoints());
    }


    @Test
    void testRemovePoints() throws Exception {
        client.addPoints(30);

        Method method = User.class.getDeclaredMethod("removePoints", int.class);
        method.setAccessible(true);

        method.invoke(client, 10);

        assertEquals(20, client.getReputationPoints());
    }


    @Test
    void testCheckPassword() {
        client.setPassword("password123");
        assertTrue(client.checkPassword("password123"));
        assertFalse(client.checkPassword("wrongpassword"));
    }

    @Test
    void testGetAuthorities() {
        List<Object> authorities = List.of();
        assertEquals(authorities, client.getAuthorities());
        assertTrue(client.isAccountNonExpired());
        assertTrue(client.isAccountNonLocked());
        assertTrue(client.isCredentialsNonExpired());
        assertTrue(client.isEnabled());
    }
}
