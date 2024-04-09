package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.persistence.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllUsers extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
