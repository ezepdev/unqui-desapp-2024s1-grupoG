package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CryptoResponse {
    private String symbol;
    private Float price;
    private LocalDateTime updated_at;
}
