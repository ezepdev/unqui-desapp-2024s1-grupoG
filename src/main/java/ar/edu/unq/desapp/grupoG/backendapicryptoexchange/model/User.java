package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

// LOMBOK ANNOTATIONS
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique=true)
    private Integer id;

    @Column(nullable = false, unique=true)
    String email;

    @Column(nullable = false, unique=true)
    String name;

    @Column(nullable = false, unique=true)
    String surname;

    @Column(nullable = false, unique=true)
    String password;

    String address;

    @Column(nullable = false,unique = true)
    BigInteger cvu;

    @Column(nullable = false,unique = true)
    Integer walletAddress;

    @Builder.Default
    Integer operationsAmount = 0;

    @Builder.Default
    Integer reputationPoints = 0;

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

}
