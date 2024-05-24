package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Crypto.CryptoResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;

import java.util.List;

public class CryptoMapper extends Mapper<CryptoCurrency, CryptoResponse> {

    public List<CryptoResponse> mapToCryptoResponses(List<CryptoCurrency> cryptos) {
        return mapTo(cryptos, CryptoMapper::mapToCryptoResponse);
    }

    public static CryptoResponse mapToCryptoResponse(CryptoCurrency crypto) {
        return new CryptoResponse(
                crypto.getSymbol().name(),
                crypto.getPrice().doubleValue(),
                crypto.getUpdated_at()
        );
    }
}
