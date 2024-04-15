package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    Integer id;
    String name;
    String surname;
    String username;
    String password;
    String email;
    Integer cvu;
    Integer wallet_direction;
}
