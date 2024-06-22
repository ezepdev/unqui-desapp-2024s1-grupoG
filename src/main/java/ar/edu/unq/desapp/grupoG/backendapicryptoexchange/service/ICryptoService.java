package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;

import java.util.List;

public interface ICryptoService {
    List<CryptoCurrency> allCurrencies();
    CryptoCurrency getCurrencyBySymbol(CryptoCurrencySymbol symbol);
    boolean isAllowedPrice(CryptoCurrencySymbol symbol , Double aPrice);
    List<CryptoCurrency> getCotizationLastTwentyFourHours(CryptoCurrencySymbol symbol);
}
