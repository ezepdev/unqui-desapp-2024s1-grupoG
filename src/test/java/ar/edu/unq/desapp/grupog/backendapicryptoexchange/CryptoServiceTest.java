package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.BinanceService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CryptoServiceTest {
    @Autowired
    private CryptoService cryptoService;

    @MockBean
    private ICryptoRepository cryptoRepository;
    @MockBean
    private BinanceService binanceService;
    private CryptoCurrency bitcoin;


    @BeforeEach
    public void setUp() {
        bitcoin = new CryptoCurrency();
        bitcoin.setSymbol(CryptoCurrencySymbol.BTCUSDT);
        bitcoin.setPrice(10000.0);
        reset(cryptoRepository);
    }
    @Test
    void testAllCurrencies() {
        CryptoCurrency currency1 = CryptoCurrency.builder().build();
        CryptoCurrency currency2 = CryptoCurrency.builder().build();
        List<CryptoCurrency> currencies = List.of(bitcoin, currency1, currency2);
        when(binanceService.getUpdatedCryptoPrices()).thenReturn(currencies);

        List<CryptoCurrency> result = cryptoService.allCurrencies();

        assertThat(result).hasSize(3);
        assertThat(result).contains(currency1, currency2, bitcoin);

    }
    @Test
    void testGetCotizationLastTwentyFourHours() {
        CryptoCurrency currency1 = CryptoCurrency.builder().build();
        CryptoCurrency currency2 = CryptoCurrency.builder().build();
        List<CryptoCurrency> currencies = List.of(bitcoin, currency1, currency2);
        when(cryptoRepository.findBySymbolAndLastTwentyFourHours(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(currencies);

        List<CryptoCurrency> result = cryptoService.getCotizationLastTwentyFourHours(CryptoCurrencySymbol.BTCUSDT);

        assertThat(result).hasSize(3);
        assertThat(result).contains(currency1, currency2, bitcoin);

    }

    @Test
    void getCurrencyBySymbol() {
        CryptoCurrencySymbol symbol = CryptoCurrencySymbol.BTCUSDT;
        when(cryptoRepository.findBySymbol(symbol)).thenReturn(bitcoin);

        CryptoCurrency result = cryptoService.getCurrencyBySymbol(symbol);

        assertThat(result).isEqualTo(bitcoin);
    }

    @Test
    public void testIsAllowedPrice_BelowMargin() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9400.0);

        assertThat(result).isFalse();
    }
    @Test
    public void testIsAllowedPrice() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9900.0);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsAllowedPrice_AboveMargin() {
        when(cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(CryptoCurrencySymbol.BTCUSDT.name())).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 10600.0);

        assertThat(result).isFalse();
    }
}
