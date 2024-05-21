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

        String jpql = "SELECT cc.symbol AS currencySymbol, cc.updated_at AS updatedAt, cc.price AS price " +
                "FROM crypto_currency cc " +
                "INNER JOIN ( " +
                "    SELECT currencySymbol, MAX(updatedAt) AS max_timestamp " +
                "    FROM crypto_currency " +
                "    GROUP BY currency_symbol " +
                ") grouped_cc " +
                "ON cc.currency_symbol = grouped_cc.currency_symbol " +
                "AND cc.timestamp = grouped_cc.max_timestamp";
        TypedQuery<CryptoCurrency> query = entityManager.createQuery(jpql, CryptoCurrency.class);

        return query.getResultStream().toList();
    }
}
