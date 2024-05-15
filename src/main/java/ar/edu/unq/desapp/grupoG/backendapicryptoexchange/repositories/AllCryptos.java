package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AllCryptos implements CryptoRepository {

    private final CrudRepository<CryptoCurrency, String> crudRepository;
    @Autowired
    public AllCryptos(CrudRepository<CryptoCurrency, String> crudRepository){
        this.crudRepository = crudRepository;
    }

    public <S extends CryptoCurrency> S save(S crypto){
        return crudRepository.save(crypto);
    }

    @Override
    public <S extends CryptoCurrency> Iterable<S> saveAll(Iterable<S> entities) {
        return crudRepository.saveAll(entities);
    }

    @Override
    public Optional<CryptoCurrency> findById(String s) {
        return crudRepository.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return crudRepository.existsById(s);
    }

    @Override
    public Iterable<CryptoCurrency> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public Iterable<CryptoCurrency> findAllById(Iterable<String> strings) {
        return crudRepository.findAllById(strings);
    }

    @Override
    public long count() {
        return crudRepository.count();
    }

    @Override
    public void deleteById(String s) {
        crudRepository.deleteById(s);
    }

    @Override
    public void delete(CryptoCurrency entity) {
        crudRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        crudRepository.deleteAllById(strings);
    }

    @Override
    public void deleteAll(Iterable<? extends CryptoCurrency> entities) {
        crudRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        crudRepository.deleteAll();
    }

    @Override
    public Optional<CryptoCurrency> findBySymbol(String symbol) {
        return Optional.empty();
    }
}
