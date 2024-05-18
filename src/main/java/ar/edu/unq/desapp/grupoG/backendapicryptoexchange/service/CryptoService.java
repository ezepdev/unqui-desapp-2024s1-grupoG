package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.CryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.CustomCryptoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CryptoService {
    @Autowired
    private CryptoRepository cryptorepository;
    @Autowired
    private RestTemplate restTemplate;

    public CryptoService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<CryptoCurrency> getCurrencyBySymbols(List<CryptoCurrencySymbol> symbols) {
        var url = "https://api1.binance.com/api/v3/ticker/price?symbols=" + formatToUrlParam(symbols);

        System.out.println(url);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CryptoCurrency>>() {}
        ).getBody();
    }

    private String formatToUrlParam(List<CryptoCurrencySymbol> symbols) {
        return symbols.stream().map(symbol -> "\"" + symbol.name() + "\"").collect(Collectors.joining(",","[","]"));
    }


    public List<CryptoCurrency> allCurrencies() {
        List<CryptoCurrency> allCryptos = getCurrencyBySymbols(Arrays.stream(CryptoCurrencySymbol.values()).toList());
        cryptorepository.saveAll(allCryptos);
        return allCryptos;
    }

}
