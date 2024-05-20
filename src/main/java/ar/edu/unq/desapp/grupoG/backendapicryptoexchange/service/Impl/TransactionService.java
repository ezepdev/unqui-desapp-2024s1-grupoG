package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.TransactionOfferNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TransactionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final ITransactionIntentionRepository transactionIntentionRepository;
    private final IUserRepository userRepository;

    public Transaction executeTransactionOffer(Integer transactionOfferId,TransactionIntentionRequest request){
        var transactionOffer = transactionIntentionRepository.findById(transactionOfferId);
        if (transactionOffer.isEmpty()) throw new TransactionOfferNotFound();
        var userOwner = userRepository.findById(transactionOffer.get().getCreator().getId());
        var userClient = userRepository.findById(request.getClientTransactionId());
        if (userOwner.isEmpty() || userClient.isEmpty()) throw new UserNotFound();

        return transactionRepository.save(
                Transaction.builder().
                        userOwner(userOwner.get()).
                        userClient(userClient.get()).
                        offer(transactionOffer.get()).
                        build());
    }

}
