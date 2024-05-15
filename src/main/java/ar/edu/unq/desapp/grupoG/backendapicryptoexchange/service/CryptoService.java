package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.CryptoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public String fetchDataFromApi(){
        String apiUrl = "hols";
        return apiUrl;
    }
    public List<CryptoCurrency> allCurrencies() {
        List<CryptoCurrency> allCryptos = (List<CryptoCurrency>) cryptorepository.findAll();

        return allCryptos;
    }
}
