package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class UserModelTest {
    @Autowired
    static User user;
    @MockBean
    private Transaction transaction;
    @BeforeAll
    static void setUp() {
        user = User.builder().build();
    }

    @AfterEach
    void clean() {
        user = User.builder().build();
    }

    @Test
    void testAnyUserCanAddReputationPoint() {
        Integer points = 30;
        Integer started_points = user.getReputationPoints();
        user.addPoints(points);
        assertEquals(started_points + points, user.getReputationPoints());
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
        assertEquals(1L, user.getId());
    }

//    @Test
//    public void testExecute_ConfirmTransferActionAsBuyer_ShouldCallConfirmTransfer() {
//        user.setId(1L);
//        TransactionIntention intention = TransactionIntention.builder().type(OperationType.VENTA).build();
//        when(transaction.getIntention()).thenReturn(intention);
//        when(transaction.getUserClient()).thenReturn(user);
//
//
//        // Act
//        user.execute(TransactionAction.CONFIRM_TRANSFER, transaction);
//
//        // Assert
//        verify(transaction, times(1)).confirmTransfer();
//        verify(transaction, times(1)).getIntention();
//    }
//
//    @Test
//    public void testExecute_ConfirmTransferActionAsSeller_ShouldThrowUpdateActionNotAllowedException() {
//        // Arrange
//        when(transaction.getIntention().getType()).thenReturn(OperationType.COMPRA);
//        when(transaction.getUserOwner().getId()).thenReturn(user.getId());
//
//        // Act & Assert
//        assertThrows(UpdateActionNotAllowed.class, () -> user.execute(TransactionAction.CONFIRM_TRANSFER, transaction));
//    }
}
