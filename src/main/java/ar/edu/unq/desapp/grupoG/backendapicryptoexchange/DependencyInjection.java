package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class DependencyInjection {

    @Bean
    @Profile("dev")
    public CryptoRepository injectDevRepository(CrudRepository<CryptoCurrency, String> repository) {
        return new AllCryptos(repository);
    }


}
