package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: SEE TRANSACTIONABLE IMPLEMENTATION
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CryptoService implements ICryptoService {
    @Autowired
    private ICryptoRepository cryptorepository;



    public List<CryptoCurrency> allCurrencies() {
        return cryptorepository.retrieveLatestCryptoPrices();
    }

    @Override
    public CryptoCurrency getCurrencyBySymbol(CryptoCurrencySymbol symbol) {
        return cryptorepository.findBySymbol(symbol);
    }


}
