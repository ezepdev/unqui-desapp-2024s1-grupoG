package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TradedVolume> tradedVolumeCryptosBetweenDates(@Param("id") Long id, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate) {


        var jpql =
                "SELECT new TradedVolume(t.intention.cryptoSymbol AS symbol, SUM(t.intention.cryptoAmount)) AS volume " +
                        "FROM Transaction t " +
                        "WHERE t.status = 'SUCCESS' " +
                        "AND t.userOwner.id = :id OR t.userClient.id = :id " +
                        "AND t.created_at > :fromDate AND t.created_at < :toDate " +
                        "GROUP BY symbol";

        TypedQuery<TradedVolume> query = entityManager.createQuery(jpql, TradedVolume.class);

        query.setParameter("id", id);
        query.setParameter("fromDate", LocalDateTime.from(fromDate.atStartOfDay()));
        query.setParameter("toDate", LocalDateTime.from(toDate.atStartOfDay()));

        return query.getResultList();
    }
}
