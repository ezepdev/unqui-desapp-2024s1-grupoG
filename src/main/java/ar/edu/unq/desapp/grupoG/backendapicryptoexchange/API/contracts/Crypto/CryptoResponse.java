package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Crypto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public record CryptoResponse(
    String symbol,
    Double price,
    String updated_at
){}
