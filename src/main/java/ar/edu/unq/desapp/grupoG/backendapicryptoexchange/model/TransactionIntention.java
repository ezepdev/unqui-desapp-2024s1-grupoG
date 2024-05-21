package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transaction_offers")
public class TransactionIntention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique=true)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type",nullable = false)
    private OperationType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "crypto_symbol",nullable = false)
    private CryptoCurrencySymbol cryptoSymbol;
    @Column(nullable = false)
    private Double cryptoPrice;
    @Column(nullable = false)
    private Double cryptoAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User creator;
    @Column(nullable = false, unique=true)
    private LocalDateTime creationDate;
}
