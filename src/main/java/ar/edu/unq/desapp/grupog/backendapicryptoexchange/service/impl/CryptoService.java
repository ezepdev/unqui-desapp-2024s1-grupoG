package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.BinanceService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ICryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoService implements ICryptoService {

    private final ICryptoRepository cryptoRepository;
    private final BinanceService binanceService;
    private static final Double PRICE_VARIATION_MARGIN = 0.05;
    private static final int UPDATE_INTERVAL = 60000;

    @Cacheable(value = "crypto")
    public List<CryptoCurrency> allCurrencies() {
        return binanceService.getUpdatedCryptoPrices();
    }

    public List<CryptoCurrency> getCotizationLastTwentyFourHours(CryptoCurrencySymbol symbol) {
        return cryptoRepository.findBySymbolAndLastTwentyFourHours(symbol.name());
    }

    @Override
    public CryptoCurrency getCurrencyBySymbol(CryptoCurrencySymbol symbol) {
        return cryptoRepository.findBySymbol(symbol);
    }

    public boolean isAllowedPrice(CryptoCurrencySymbol symbol , Double aPrice) {
        var currentCurrency = cryptoRepository.retrieveCurrentPriceForCryptoWithSymbol(symbol.name());
        var priceVariation = currentCurrency.getPrice() * PRICE_VARIATION_MARGIN;
        var minPriceVariation = currentCurrency.getPrice() - priceVariation;
        var maxPriceVariation = currentCurrency.getPrice() + priceVariation;
        return aPrice > minPriceVariation && aPrice < maxPriceVariation;
    }

    @Scheduled(fixedRate = UPDATE_INTERVAL)
    protected void registerCurrenciesPrice() {
        cryptoRepository.saveAll(binanceService.getUpdatedCryptoPrices());
    }

}
