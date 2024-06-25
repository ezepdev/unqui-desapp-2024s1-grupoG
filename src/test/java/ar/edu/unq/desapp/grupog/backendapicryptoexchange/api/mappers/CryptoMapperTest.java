package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoMapperTest {

    CryptoMapper cryptoMapper;

    @Test
    void mapToCryptoResponses() {
        CryptoCurrency cryptoCurrency = CryptoCurrency.builder().symbol(CryptoCurrencySymbol.ETHUSDT).price(100.0).build();
        CryptoCurrency cryptoCurrency2 = CryptoCurrency.builder().symbol(CryptoCurrencySymbol.BTCUSDT).price(200.0).build();
        CryptoCurrency cryptoCurrency3 = CryptoCurrency.builder().symbol(CryptoCurrencySymbol.ETHUSDT).price(300.0).build();
        var cryptos = CryptoMapper.mapToCryptoResponses(List.of(cryptoCurrency, cryptoCurrency2, cryptoCurrency3));
        assertEquals(3, cryptos.size());
        assertEquals(cryptoCurrency.getSymbol().name(), cryptos.get(0).symbol());
        assertEquals(cryptoCurrency.getPrice().doubleValue(), cryptos.get(0).price());
        assertEquals(cryptoCurrency2.getSymbol().name(), cryptos.get(1).symbol());
        assertEquals(cryptoCurrency2.getPrice().doubleValue(), cryptos.get(1).price());

        assertEquals(cryptoCurrency3.getSymbol().name(), cryptos.get(2).symbol());
        assertEquals(cryptoCurrency3.getPrice().doubleValue(), cryptos.get(2).price());
    }
}
