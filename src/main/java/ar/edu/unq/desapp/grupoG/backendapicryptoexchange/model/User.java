package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

// LOMBOK ANNOTATIONS
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique=true)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,unique = true)
    private String cvu;

    @Column(nullable = false,unique = true)
    private String walletAddress;

    @Builder.Default
    private Integer operationsAmount = 0;

    @Builder.Default
    private Integer reputationPoints = 0;

    public String username() {
        return name + surname;
    }
    public void addPoints(Integer points) {
        reputationPoints += points;
    }

    public void addOperation() {
        operationsAmount++;
    }

    public Double get_reputation() {
        if (operationsAmount == 0) return 0.0; // TODO: CHECK THIS
        return (double)reputationPoints / operationsAmount;
    }

    public void execute(TransactionAction action, Transaction transaction) {
        switch (action) {
            case CONFIRM_TRANSFER:
                if (this.isBuyer(transaction))
                    transaction.setState(TransactionStatus.TRANSFER_SUCCESS);
                break;
            case CONFIRM_RECEIPT:
                if (this.isSeller(transaction))
                    transaction.setState(TransactionStatus.SUCCESS);
                break;
            case CANCEL:
                transaction.setState(TransactionStatus.CANCELED);
                break;
        }

    }

    private boolean isBuyer (Transaction transaction){
        return transaction.getIntention().getType() == OperationType.COMPRA && transaction.getUserOwner().getId().equals(id) || transaction.getIntention().getType() == OperationType.VENTA && transaction.getUserClient().getId().equals(id);

    }

    private boolean isSeller (Transaction transaction){
        return transaction.getIntention().getType() == OperationType.COMPRA && transaction.getUserClient().getId().equals(id) || transaction.getIntention().getType() == OperationType.VENTA && transaction.getUserOwner().getId().equals(id);
    }
}
