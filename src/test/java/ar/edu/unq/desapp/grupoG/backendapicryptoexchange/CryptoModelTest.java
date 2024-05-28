package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CryptoModelTest {

    @Autowired
        static CryptoCurrency any_currency;

        @BeforeEach
        void setUp() {
            any_currency = CryptoCurrency.builder().build();
        }

        @AfterEach
        void clean() {
            any_currency = CryptoCurrency.builder().build();
        }

        @Test
        void testAnyCryptoCurrencyCanGiveCreationTime() {
            LocalDateTime started_at = LocalDateTime.now();
            String created_at = any_currency.getUpdated_at();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
            assertEquals(created_at, started_at.format(formatter));
        }


}
