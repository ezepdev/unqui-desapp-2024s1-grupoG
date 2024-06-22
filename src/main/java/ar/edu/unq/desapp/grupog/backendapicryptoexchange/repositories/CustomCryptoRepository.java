package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;

import java.util.List;

public interface CustomCryptoRepository {
    List<CryptoCurrency> retrieveLatestCryptoPrices();
}

