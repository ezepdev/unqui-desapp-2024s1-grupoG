package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    // TODO: CHECK THIS: Date priceDate = Date.from(Instant.now());
    // LAST UPDATED DATE?
    @Builder.Default
    private LocalDateTime priceDate = LocalDateTime.now();

    public String getPriceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
        return priceDate.format(formatter);
    }
}