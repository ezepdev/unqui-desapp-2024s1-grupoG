package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.*;
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
@Table(name = "crypto_currency")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "crypto_symbol",nullable = false)
    CryptoCurrencySymbol symbol;
    @Column(nullable = false)
    Double price;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime updated_at = LocalDateTime.now();

    public String getUpdated_at() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
        return updated_at.format(formatter);
    }
}