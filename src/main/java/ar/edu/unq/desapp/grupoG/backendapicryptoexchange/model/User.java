package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    Integer wallet_address;

    @Builder.Default
    Integer operations_amount = 0;

    @Builder.Default
    Integer reputation_points = 0;

    public String username() {
        return name + surname;
    }
    public void addPoints(Integer points) {
        reputation_points += points;
    }

    public void addOperation() {
        operations_amount++;
    }

    public Double get_reputation() {
        if (operations_amount == 0) return 0.0; // TODO: CHECK THIS
        return (double)reputation_points / operations_amount;
    }

}
