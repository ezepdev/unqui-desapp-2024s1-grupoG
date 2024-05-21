package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.CryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.BinanceService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CryptoService implements ICryptoService {
    @Autowired
    private CryptoRepository cryptorepository;



    public List<CryptoCurrency> allCurrencies() {
        System.out.println(cryptorepository.findAll());
        List<CryptoCurrency> allCryptos = cryptorepository.retrieveLatestCryptoPrices();
        return allCryptos;
    }

    @Override
    public CryptoCurrency getCurrencyBySymbol(CryptoCurrencySymbol symbol) {
        return cryptorepository.findBySymbol(symbol);
    }


}
