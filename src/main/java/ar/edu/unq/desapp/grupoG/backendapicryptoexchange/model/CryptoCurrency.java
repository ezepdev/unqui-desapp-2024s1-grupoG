package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class CryptoCurrency {
    @Id
    String symbol;
    Float price;
    Date price_date = Date.from(Instant.now());
}