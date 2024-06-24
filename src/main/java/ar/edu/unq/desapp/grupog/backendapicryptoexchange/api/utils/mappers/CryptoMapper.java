package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.crypto.CryptoResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;

import java.util.List;

public class CryptoMapper extends Mapper<CryptoCurrency, CryptoResponse> {

    public List<CryptoResponse> mapToCryptoResponses(List<CryptoCurrency> cryptos) {
        return mapTo(cryptos, CryptoMapper::mapToCryptoResponse);
    }

    public static CryptoResponse mapToCryptoResponse(CryptoCurrency crypto) {
        return new CryptoResponse(
                crypto.getSymbol().name(),
                crypto.getPrice(),
                crypto.getUpdatedAt()
        );
    }
}
