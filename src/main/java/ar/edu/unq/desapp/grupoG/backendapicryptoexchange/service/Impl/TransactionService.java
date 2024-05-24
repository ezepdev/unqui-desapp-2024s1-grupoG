package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.*;
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

        //* Verify transaction intention exists and is active
        Optional<TransactionIntention> transactionIntention = transactionIntentionRepository.findById(request.transaction_intention_id());
        if (transactionIntention.isEmpty() || transactionIntention.get().getState().equals(TransactionIntentionState.INACTIVE)) throw new TransactionIntentionNotFound();

        //* Verify that the user who initiated the transaction and the user who created the intention exists
        Long transactionIntentionCreatorId = transactionIntention.get().getCreator().getId();
        var userOwner = userRepository.findById(transactionIntentionCreatorId);
        var userClient = userRepository.findById(request.transaction_starter_user_id());
        if (userOwner.isEmpty() || userClient.isEmpty()) throw new UserNotFound();

        //* Verify that the user who created the intention and the user who initiated the transaction are not the same
        checkUserIsNotTheSame(transactionIntentionCreatorId, request.transaction_starter_user_id());

        //* Create transaction
        return transactionRepository.save(
                Transaction.builder().
                        userOwner(userOwner.get()).
                        userClient(userClient.get()).
                        intention(transactionIntention.get()).
                        build());
    }

    @Override
    public Transaction updateTransactionStatus(Integer transactionId, UpdateTransactionRequest request) {

        var transaction = transactionRepository.findById(transactionId);
        Optional<User> user_updater = userRepository.findById(request.getUser_id());

        if (user_updater.isEmpty()) throw new UserNotFound();
        if (transaction.isEmpty()) throw new TransactionIntentionNotFound();
        user_updater.get().execute(TransactionAction.valueOf(request.getAction()),transaction.get());
        userRepository.save(user_updater.get());
        return transactionRepository.save(transaction.get());

    }

    //* PRIVATE METHODS
    private void checkUserIsNotTheSame(Long transactionIntentionCreatorId, Long aLong) {
        if (transactionIntentionCreatorId.equals(aLong)) throw InvalidTransaction.builder().description("Transaction cannot be started by the user who created it").build();
    }

}
