package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
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
    private CryptoCurrency bitcoin;


    @BeforeEach
    public void setUp() {
        bitcoin = new CryptoCurrency();
        bitcoin.setSymbol(CryptoCurrencySymbol.BTCUSDT);
        bitcoin.setPrice(10000.0);
        reset(cryptoRepository);
    }

    @Test
    void allCurrencies() {
        List<CryptoCurrency> currencies = new ArrayList<>();
        CryptoCurrency currency1 = CryptoCurrency.builder().build();
        CryptoCurrency currency2 = CryptoCurrency.builder().build();
        currencies.add(currency1);
        currencies.add(currency2);
        when(cryptoRepository.retrieveLatestCryptoPrices()).thenReturn(currencies);

        List<CryptoCurrency> result = cryptoService.allCurrencies();

        assertThat(result).hasSize(2);
        assertThat(result).contains(currency1, currency2);

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
        when(cryptoRepository.findBySymbol(CryptoCurrencySymbol.BTCUSDT)).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9400.0);

        assertThat(result).isFalse();
    }
    @Test
    public void testIsAllowedPrice() {
        when(cryptoRepository.findBySymbol(CryptoCurrencySymbol.BTCUSDT)).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 9900.0);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsAllowedPrice_AboveMargin() {
        when(cryptoRepository.findBySymbol(CryptoCurrencySymbol.BTCUSDT)).thenReturn(bitcoin);

        boolean result = cryptoService.isAllowedPrice(CryptoCurrencySymbol.BTCUSDT, 10600.0);

        assertThat(result).isFalse();
    }
}
