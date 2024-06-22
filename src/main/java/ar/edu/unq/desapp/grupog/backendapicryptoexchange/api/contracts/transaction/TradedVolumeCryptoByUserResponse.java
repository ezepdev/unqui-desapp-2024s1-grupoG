package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction;

import java.time.LocalDateTime;
import java.util.List;

public record TradedVolumeCryptoByUserResponse (
        LocalDateTime date,
        Double total_volume_in_dollars,
        Double total_volume_in_pesos,
        List<CryptoCotization> crypto_cotizations
){
}
