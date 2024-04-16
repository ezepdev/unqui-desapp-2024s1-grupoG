package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    String name;
    String surname;
    String password;
    @Id
    @Column(nullable = false, unique=true)
    String email;
    String address;
    @Column(nullable = false,unique = true)
    BigInteger cvu;
    @Column(nullable = false,unique = true)
    Integer wallet_address;

    public String username() {
        return name + surname;
    }
}
