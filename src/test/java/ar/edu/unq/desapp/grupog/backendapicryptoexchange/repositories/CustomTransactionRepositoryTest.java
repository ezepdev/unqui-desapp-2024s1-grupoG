package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = {EntityManager.class,
                EntityManagerFactory.class,
                CustomTransactionRepositoryImpl.class,
        }
)
class CustomTransactionRepositoryTest {

    @MockBean
    private EntityManager entityManager;
    @MockBean
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private CustomTransactionRepositoryImpl transactionRepository;

    @BeforeEach
    public void setup() {
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    void testTradedVolumeCryptosBetweenDates() {

        LocalDate fromDate = LocalDate.now().minusDays(1);
        Long userId = 1L;
        List<TradedVolume> tradedVolume = List.of(TradedVolume.builder().symbol(CryptoCurrencySymbol.AAVEUSDT).volume(10L).currentPrice(35.0).build(),TradedVolume.builder().symbol(CryptoCurrencySymbol.BTCUSDT).volume(50L).currentPrice(35.0).build());

        TypedQuery<TradedVolume> query = (TypedQuery<TradedVolume>) mock(TypedQuery.class);

        when(entityManager.createQuery(anyString(),eq(TradedVolume.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(tradedVolume);

        List<TradedVolume> result = transactionRepository.tradedVolumeCryptosBetweenDates(userId, fromDate, LocalDate.now());

        assertEquals(2, result.size());
        assertEquals(CryptoCurrencySymbol.AAVEUSDT, result.get(0).getSymbol());
        assertEquals(CryptoCurrencySymbol.BTCUSDT, result.get(1).getSymbol());
        assertEquals(result, tradedVolume);
    }
}
