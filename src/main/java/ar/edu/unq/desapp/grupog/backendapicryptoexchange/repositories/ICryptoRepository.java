package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.CryptoCurrencySymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICryptoRepository extends JpaRepository<CryptoCurrency,String> {
    @Query(value = "SELECT TOP 1 cc.* " +
            "FROM crypto_currency cc " +
            "WHERE cc.crypto_symbol = :symbol " +
            "ORDER BY cc.updated_at DESC ",
            nativeQuery = true)
    CryptoCurrency retrieveCurrentPriceForCryptoWithSymbol(String symbol);
    @Query(value = "SELECT cc.* " +
            "FROM crypto_currency cc " +
            "INNER JOIN ( " +
            "    SELECT crypto_symbol, MAX(updated_at) AS updated_at " +
            "    FROM crypto_currency " +
            "    GROUP BY crypto_symbol " +
            ") latest " +
            "ON cc.crypto_symbol = latest.crypto_symbol " +
            "AND cc.updated_at = latest.updated_at",
            nativeQuery = true)
    List<CryptoCurrency> retrieveLatestCryptoPrices();
    @Query(value = "SELECT cc.* " +
            "FROM crypto_currency cc " +
            "WHERE cc.crypto_symbol = :symbol " +
            "ORDER BY cc.updated_at DESC " +
            "LIMIT 24",
            nativeQuery = true)
    List<CryptoCurrency> findBySymbolAndLastTwentyFourHours(String symbol);
    CryptoCurrency findBySymbol(CryptoCurrencySymbol symbol);
}
