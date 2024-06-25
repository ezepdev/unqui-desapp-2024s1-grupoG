package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.crypto.CryptoResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CryptoMapper {

    public static List<CryptoResponse> mapToCryptoResponses(List<CryptoCurrency> cryptos) {
        return cryptos.stream().map(CryptoMapper::mapToCryptoResponse).toList();
    }

    public static CryptoResponse mapToCryptoResponse(CryptoCurrency crypto) {
        return new CryptoResponse(
                crypto.getSymbol().name(),
                crypto.getPrice(),
                crypto.getUpdatedAt()
        );
    }
}
