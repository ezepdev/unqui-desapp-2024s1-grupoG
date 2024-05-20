package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class CryptoResponse {
    private String symbol;
    private Float price;
    private LocalDateTime date;
}
