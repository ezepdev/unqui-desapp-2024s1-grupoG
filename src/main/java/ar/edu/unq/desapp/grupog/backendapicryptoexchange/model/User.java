package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.UpdateActionNotAllowed;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// LOMBOK ANNOTATIONS
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique=true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String cvu;

    @Column(nullable = false)
    private String walletAddress;

    @Builder.Default
    private Integer operationsAmount = 0;

    @Builder.Default
    private Integer reputationPoints = 0;

    public void addPoints(Integer points) {
        reputationPoints += points;
    }

    public void addOperation() {
        operationsAmount++;
    }

    public Double getReputation() {
        if (operationsAmount == 0) return 0.0;
        return (double)reputationPoints / operationsAmount;
    }

    public void execute(TransactionAction action, Transaction transaction) {
        switch (action) {
            case CONFIRM_TRANSFER:
                if (this.isBuyer(transaction))
                    transaction.confirmTransfer();
                if (this.isSeller(transaction)) {
                    throw new UpdateActionNotAllowed(action.name());
                }
                break;
            case CONFIRM_RECEIPT:
                if (this.isSeller(transaction))
                    transaction.confirmReceipt();
                    updateReputation(transaction);
                if (this.isBuyer(transaction)) {
                    throw new UpdateActionNotAllowed(action.name());
                }
                break;
            case CANCEL:
                transaction.cancel();
                removePoints(20);
                break;
            default:
                throw new UpdateActionNotAllowed(action.name());
        }

    }


    private void updateReputation(Transaction transaction) {
        if (LocalDateTime.now().isBefore(transaction.getCreatedAt().plusMinutes(30))) {
            transaction.getUserClient().addPoints(10);
            transaction.getUserOwner().addPoints(10);
        }
        else {
            transaction.getUserClient().addPoints(5);
            transaction.getUserOwner().addPoints(5);
        }
        transaction.getUserClient().addOperation();
        transaction.getUserOwner().addOperation();
    }

    private void removePoints(int points) {
        reputationPoints -= points;
    }

    private boolean isBuyer (Transaction transaction){
        return transaction.getIntention().getType() == OperationType.COMPRA && transaction.getUserOwner().getId().equals(id) || transaction.getIntention().getType() == OperationType.VENTA && transaction.getUserClient().getId().equals(id);

    }

    private boolean isSeller (Transaction transaction){
        return transaction.getIntention().getType() == OperationType.COMPRA && transaction.getUserClient().getId().equals(id) || transaction.getIntention().getType() == OperationType.VENTA && transaction.getUserOwner().getId().equals(id);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
