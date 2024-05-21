package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionAction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionStatus;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.InvalidTransaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.TransactionIntentionNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final ITransactionIntentionRepository transactionIntentionRepository;
    private final IUserRepository userRepository;

    @Override
    public Transaction startTransaction(StartTransactionRequest request) {

        var transactionIntention = transactionIntentionRepository.findById(request.getTransaction_intention_id());
        if (transactionIntention.isEmpty()) throw new TransactionIntentionNotFound();
        var user_owner_id = transactionIntention.get().getCreator().getId();

        if (user_owner_id == request.getClient_transaction_id()) throw InvalidTransaction.builder().description("Transaction cannot be executed by the user who created it").build();
        var userOwner = userRepository.findById(user_owner_id);
        var userClient = userRepository.findById(request.getClient_transaction_id());
        if (userOwner.isEmpty() || userClient.isEmpty()) throw new UserNotFound();

        var result = transactionRepository.save(
                Transaction.builder().
                        userOwner(userOwner.get()).
                        userClient(userClient.get()).
                        intention(transactionIntention.get()).
                        build());

        return result;
    }


    @Override
    public Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request) {

        var transaction = transactionRepository.findById(transactionId);
        Optional<User> user_updater = userRepository.findById(request.getUser_id());

        if (user_updater.isEmpty()) throw new UserNotFound();
        if (transaction.isEmpty()) throw new TransactionIntentionNotFound();
        user_updater.get().execute(TransactionAction.valueOf(request.getAction()),transaction.get());

        return transactionRepository.save(transaction.get());

    }

}
