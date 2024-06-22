package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ITransactionRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final ITransactionIntentionRepository transactionIntentionRepository;
    private final IUserRepository userRepository;
    private final ICryptoService cryptoService;
    private final IExchangeService exchangeService;
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

        //* Verify transaction exists
        Optional<Transaction> maybe_transaction = transactionRepository.findById(transactionId);
        if (maybe_transaction.isEmpty()) throw new TransactionNotFound();
        Transaction transaction = maybe_transaction.get();

        //* Verify that the user who try to update the transaction exists
        Optional<User> maybe_user_updater = userRepository.findById(request.user_id());
        if (maybe_user_updater.isEmpty()) throw new UserNotFound();
        //* Verify that the use who try to update the transaction is implicated in the transaction
        User user_updater = maybe_user_updater.get();
        if (!transaction.IsUserImplicated(user_updater)) throw new UserNotAuthorized();
        //* Verify price is within the variation margin
        if (!cryptoService.isAllowedPrice(transaction.getIntention().getCryptoSymbol(),transaction.getIntention().getCryptoPrice())) throw new PriceVariationMarginConflict();

        //* Try to update the transaction
        user_updater.execute(
                TransactionAction.valueOf(request.action()),transaction);

        return transactionRepository.save(transaction);

    }

    @Override
    public List<TradedVolume> getTransactionsByUserBetweenDates(Long id, LocalDate fromDate, LocalDate toDate) {
        var tradedVolumes = transactionRepository.tradedVolumeCryptosBetweenDates(id, fromDate, toDate);
        tradedVolumes.forEach(t -> t.setCurrent_price(cryptoService.getCurrencyBySymbol(t.getSymbol()).getPrice()));
        tradedVolumes.forEach(t -> t.setFinal_price(exchangeService.convertToArs(t.getCurrent_price() * t.getVolume())));
        return tradedVolumes;
    }

    //* PRIVATE METHODS
    private void checkUserIsNotTheSame(Long transactionIntentionCreatorId, Long aLong) {
        if (transactionIntentionCreatorId.equals(aLong)) throw InvalidTransaction.builder().description("Transaction cannot be started by the user who created it").build();
    }
}
