package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers.CryptoMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ICryptoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class CryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICryptoService cryptoService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    CryptoCurrency cryptoCurrency;
    CryptoCurrency cryptoCurrency2;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        cryptoCurrency = CryptoCurrency.builder().symbol(CryptoCurrencySymbol.BTCUSDT).price(30000.0).build();
        cryptoCurrency2 = CryptoCurrency.builder().symbol(CryptoCurrencySymbol.ETHUSDT).price(2000.0).build();

    }

    @Test
    void testGetAllCryptos() throws Exception {

        List<CryptoCurrency> mockCryptos = Arrays.asList(
                cryptoCurrency,
                cryptoCurrency2
        );
        when(cryptoService.allCurrencies()).thenReturn(mockCryptos);


        String expectedJson = new ObjectMapper().writeValueAsString(CryptoMapper.mapToCryptoResponses(mockCryptos));

        mockMvc.perform(get("/cryptos"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void testGetCotizationLastTwentyFourHours() throws Exception {

        List<CryptoCurrency> mockCryptos = Arrays.asList(
                cryptoCurrency,
                cryptoCurrency2
        );

        when(cryptoService.getCotizationLastTwentyFourHours(CryptoCurrencySymbol.BTCUSDT)).thenReturn(mockCryptos);


        String expectedJson = new ObjectMapper().writeValueAsString(CryptoMapper.mapToCryptoResponses(mockCryptos));

        mockMvc.perform(get("/cryptos/BTCUSDT"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void testGetCotizationLastTwentyFourHours_NotFound() throws Exception {

        when(cryptoService.getCotizationLastTwentyFourHours(any(CryptoCurrencySymbol.class)))
                .thenThrow(new IllegalArgumentException("Crypto symbol not exist, please check this"));

        mockMvc.perform(get("/cryptos/INVALID"))
                .andExpect(status().isBadRequest());
    }
}
