package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "transaction_intentions")
public class TransactionIntention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique=true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type",nullable = false)
    private OperationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "crypto_symbol",nullable = false)
    private CryptoCurrencySymbol cryptoSymbol;

    @Column(nullable = false)
    private Double cryptoPrice;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @Builder.ObtainVia(method="setCryptoAmount")

    private Integer cryptoAmount;

    @Column(nullable = false)
    private Long finalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User creator;

    @Column(nullable = false, unique=true)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state",nullable = false)
    @Builder.Default
    private TransactionIntentionState state = TransactionIntentionState.ACTIVE;
}
