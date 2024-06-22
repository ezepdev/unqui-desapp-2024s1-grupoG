package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BinanceService {

    @Autowired
    private final RestTemplate restTemplate;

    public List<CryptoCurrency> getUpdatedCryptoPrices() {
            var url = "https://api1.binance.com/api/v3/ticker/price?symbols=" + formatToUrlParams(Arrays.stream(CryptoCurrencySymbol.values()).toList());

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CryptoCurrency>>() {}
            ).getBody();
        }

        private String formatToUrlParams(List<CryptoCurrencySymbol> symbols) {
            return symbols.stream().map(symbol -> "\"" + symbol.name() + "\"").collect(Collectors.joining(",","[","]"));
        }

}
