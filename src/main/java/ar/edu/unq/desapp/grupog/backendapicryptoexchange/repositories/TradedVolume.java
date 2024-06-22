package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class TradedVolume {
    private final CryptoCurrencySymbol symbol;
    private final Long volume;
    private Double current_price;
    private Long final_price;
}
