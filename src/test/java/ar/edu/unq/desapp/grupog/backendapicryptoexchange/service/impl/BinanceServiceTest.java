package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.BinanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")

class BinanceServiceTest {
    @Autowired
    private BinanceService binanceService;
    @MockBean
    private RestTemplate restTemplate;



    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetUpdatedCryptoPrices() {
        // Preparar datos de prueba
        CryptoCurrency crypto1 = new CryptoCurrency();
        crypto1.setSymbol(CryptoCurrencySymbol.BTCUSDT);
        crypto1.setPrice(30000.0);

        CryptoCurrency crypto2 = new CryptoCurrency();
        crypto2.setSymbol(CryptoCurrencySymbol.ETHUSDT);
        crypto2.setPrice(2000.0);

        List<CryptoCurrency> mockResponse = List.of(crypto1, crypto2);

        ParameterizedTypeReference<List<CryptoCurrency>> typeRef = new ParameterizedTypeReference<>() {};

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                eq(typeRef)
        )).thenReturn(ResponseEntity.ok(mockResponse));


        List<CryptoCurrency> result = binanceService.getUpdatedCryptoPrices();


        assertEquals(2, result.size());
        assertEquals(30000.0, result.get(0).getPrice());
        assertEquals(2000.0, result.get(1).getPrice());


        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate, times(1)).exchange(
                urlCaptor.capture(),
                eq(HttpMethod.GET),
                isNull(),
                eq(typeRef)
        );

        String expectedUrl = "https://api1.binance.com/api/v3/ticker/price?symbols=[\"ALICEUSDT\",\"MATICUSDT\",\"AXSUSDT\",\"AAVEUSDT\",\"ATOMUSDT\",\"NEOUSDT\",\"DOTUSDT\",\"ETHUSDT\",\"CAKEUSDT\",\"BTCUSDT\",\"BNBUSDT\",\"ADAUSDT\",\"TRXUSDT\",\"AUDIOUSDT\"]";
        assertEquals(expectedUrl, urlCaptor.getValue());
    }
}
