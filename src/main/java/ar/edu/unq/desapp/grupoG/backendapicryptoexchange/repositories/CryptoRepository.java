package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoCurrency,String> {
    @Query(value = "SELECT cc.* " +
            "FROM crypto_currency cc " +
            "INNER JOIN ( " +
            "    SELECT symbol, MAX(updated_at) AS updated_at " +
            "    FROM crypto_currency " +
            "    GROUP BY symbol " +
            ") latest " +
            "ON cc.symbol = latest.symbol " +
            "AND cc.updated_at = latest.updated_at",
            nativeQuery = true)
    List<CryptoCurrency> retrieveLatestCryptoPrices();
}
