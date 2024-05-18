package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;

import java.util.Optional;

public interface CustomCryptoRepository {
    Optional<CryptoCurrency> findBySymbol(String symbol);
}

