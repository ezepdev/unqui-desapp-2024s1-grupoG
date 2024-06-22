package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

