package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Console;
import java.util.Optional;

public class AllCryptos implements CustomCryptoRepository {

    private final CrudRepository<CryptoCurrency, String> crudRepository;

    @Autowired
    public AllCryptos(CrudRepository<CryptoCurrency, String> crudRepository) {
        this.crudRepository = crudRepository;
    }
    @Override
    public Optional<CryptoCurrency> findBySymbol(String symbol) {
        System.out.println("aqui es donde queremos que pase");
        return Optional.of(new CryptoCurrency());
    }
}
