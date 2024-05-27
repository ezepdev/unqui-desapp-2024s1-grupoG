package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITransactionRepository extends CrudRepository<Transaction,Integer> {
    @Query(value="SELECT t.* FROM transactions t WHERE (t.user_owner_id = :id OR t.user_client_id = :id) AND t.created_at BETWEEN :fromDate AND :toDate AND t.transaction_status = 'SUCCESS'", nativeQuery = true)
    List<Transaction> findByUserIdAndCreatedAtBetween(Long id, LocalDate fromDate, LocalDate toDate);
}
