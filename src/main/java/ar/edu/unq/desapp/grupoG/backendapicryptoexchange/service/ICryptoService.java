package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;

import java.util.List;

public interface ICryptoService {
    List<CryptoCurrency> allCurrencies();
}
