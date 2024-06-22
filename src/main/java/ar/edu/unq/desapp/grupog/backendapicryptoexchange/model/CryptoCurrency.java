package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency implements Serializable {
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