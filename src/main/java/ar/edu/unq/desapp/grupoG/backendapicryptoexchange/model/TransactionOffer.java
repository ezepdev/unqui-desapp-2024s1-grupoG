package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_offers")
@Entity
public class TransactionOffer {
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
    @Column(nullable = false, unique=true)
    private Float cryptoPrice;
    @Column(nullable = false, unique=true)
    private Float cryptoAmount;
    @Column(nullable = false, unique=true)
    private Float finalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User creator;
    @Column(nullable = false, unique=true)
    private Date creationDate;
}
