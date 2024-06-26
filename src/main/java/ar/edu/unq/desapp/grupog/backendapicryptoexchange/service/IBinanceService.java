package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;

import java.util.List;

public interface IBinanceService {
   List<CryptoCurrency> getUpdatedCryptoPrices();
}
