package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AllUsers extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
