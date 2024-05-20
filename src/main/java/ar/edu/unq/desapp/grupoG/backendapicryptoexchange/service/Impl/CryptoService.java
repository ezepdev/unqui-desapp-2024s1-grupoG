package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.CryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION
@Service
@NoArgsConstructor
public class CryptoService implements ICryptoService {
    @Autowired
    private CryptoRepository cryptorepository;
    @Autowired
    private RestTemplate restTemplate;

    public CryptoService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private List<CryptoCurrency> getCurrencyBySymbols(List<CryptoCurrencySymbol> symbols) {
        var url = "https://api1.binance.com/api/v3/ticker/price?symbols=" + formatToUrlParams(symbols);

        System.out.println(url);
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


    public List<CryptoCurrency> allCurrencies() {
        List<CryptoCurrency> allCryptos = getCurrencyBySymbols(Arrays.stream(CryptoCurrencySymbol.values()).toList());
        cryptorepository.saveAll(allCryptos);
        return allCryptos;
    }

}
