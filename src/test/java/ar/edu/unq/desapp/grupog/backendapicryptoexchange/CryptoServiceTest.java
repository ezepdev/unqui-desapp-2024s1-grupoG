package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IBinanceService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
class CryptoServiceTest {
    private CryptoCurrency bitcoin;
    private CryptoCurrency currency1;
    private CryptoCurrency currency2;
    @Autowired
    private CryptoService cryptoService;
    @MockBean
    private IBinanceService binanceService;
    @MockBean
    private ICryptoRepository cryptoRepository;



    @BeforeEach
    public void setUp() {
        reset(cryptoRepository);
        bitcoin = CryptoCurrency.builder().build();
        bitcoin.setSymbol(CryptoCurrencySymbol.BTCUSDT);
        bitcoin.setPrice(10000.0);
        bitcoin.setUpdatedAt(LocalDateTime.now());
        currency1 = CryptoCurrency.builder().build();
        currency2 = CryptoCurrency.builder().build();
        currency1.setUpdatedAt(LocalDateTime.now().minusHours(1));
        currency2.setUpdatedAt(LocalDateTime.now().minusHours(2));

    }

    @Test
    void testGetCotizationLastTwentyFourHours() {
        List<CryptoCurrency> currencies = List.of(bitcoin, currency1, currency2);
        when(cryptoRepository.findBySymbolAndLastTwentyFourHours(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(currencies);

        List<CryptoCurrency> result = cryptoService.getCotizationLastTwentyFourHours(CryptoCurrencySymbol.BTCUSDT);

        assertThat(result).hasSize(3);
        assertThat(result).contains(currency1, currency2, bitcoin);
    }

    @Test
    void testAllCurrencies() {
        List<CryptoCurrency> currencies = List.of(bitcoin, currency1, currency2);
        when(binanceService.getUpdatedCryptoPrices()).thenReturn(currencies);

        List<CryptoCurrency> result = cryptoService.allCurrencies();

        assertThat(result).hasSize(3).contains(currency1, currency2, bitcoin);

    }


    @Test
    void getCurrencyBySymbol() {
        CryptoCurrencySymbol symbol = CryptoCurrencySymbol.BTCUSDT;
        when(cryptoRepository.findBySymbol(symbol)).thenReturn(bitcoin);

        CryptoCurrency result = cryptoService.getCurrencyBySymbol(symbol);

        assertThat(result).isEqualTo(bitcoin);
    }

    @Test
    void testIsAllowedPrice_BelowMargin() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9400.0);

        assertThat(result).isFalse();
    }
    @Test
    void testIsAllowedPrice() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9900.0);

        assertThat(result).isTrue();
    }

    @Test
    void testIsAllowedPrice_AboveMargin() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 10600.0);

        assertThat(result).isFalse();
    }
}
