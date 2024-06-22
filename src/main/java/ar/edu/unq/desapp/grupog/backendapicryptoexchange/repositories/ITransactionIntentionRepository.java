package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntentionState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionIntentionRepository extends CrudRepository<TransactionIntention,Integer> {
    List<TransactionIntention> findByState(TransactionIntentionState state);
}
