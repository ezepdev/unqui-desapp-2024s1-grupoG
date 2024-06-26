package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BinanceService implements IBinanceService {

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
