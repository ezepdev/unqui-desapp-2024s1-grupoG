package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.OperationType;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionIntentionService implements ITransactionIntentionService {

    private ITransactionIntentionRepository transactionIntentionRepository;
    private IUserRepository userRepository;

    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request) {
        Optional<User> userResult = userRepository.findById(request.getCreator_transaction_id());
        if (userResult.isEmpty()) throw new UserNotFound();
        TransactionIntention transactionIntention =
                TransactionIntention.builder()
                        .type(OperationType.valueOf(request.getOperation_type()))
                        .creationDate(LocalDate.now())
                        .creator(userResult.get())
                        .cryptoSymbol(CryptoCurrencySymbol.valueOf((request.getCrypto_symbol())))
                        .cryptoPrice(request.getPrice())
                        .cryptoAmount(request.getAmount())
                        .build();

        return transactionIntentionRepository.save(transactionIntention);
    }
}
