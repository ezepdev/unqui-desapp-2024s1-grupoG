package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOfferRepository  extends CrudRepository<TransactionOffer,Integer> {
}
