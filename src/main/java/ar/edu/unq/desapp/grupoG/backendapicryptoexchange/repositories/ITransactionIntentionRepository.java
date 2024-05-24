package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntentionState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITransactionIntentionRepository extends CrudRepository<TransactionIntention,Integer> {
    List<TransactionIntention> findByState(TransactionIntentionState state);
}
