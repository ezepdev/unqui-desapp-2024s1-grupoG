package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITransactionRepository extends CrudRepository<Transaction,Integer>,CustomTransactionRepository {

}