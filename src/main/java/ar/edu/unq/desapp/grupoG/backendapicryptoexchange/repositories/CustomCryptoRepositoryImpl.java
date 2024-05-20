package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomCryptoRepositoryImpl implements CustomCryptoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CryptoCurrency> retrieveLatestCryptoPrices() {
        String jpql = "SELECT c FROM Crypto c WHERE c.date = (SELECT MAX(c2.date) FROM Crypto c2 WHERE )";
        TypedQuery<CryptoCurrency> query = entityManager.createQuery(jpql, CryptoCurrency.class);
        return query.getResultList();
    }
}
