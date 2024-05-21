package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.PriceVariationMarginConflict;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IExchangeService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class TransactionIntentionService implements ITransactionIntentionService {

    private ITransactionIntentionRepository transactionIntentionRepository;
    private IExchangeService exchangeService;
    private final ITransactionRepository ITransactionRepository;
    private ICryptoService cryptoService;
    private IUserRepository userRepository;

    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request) {
        Optional<User> userResult = userRepository.findById(request.getCreator_transaction_id());
        if (userResult.isEmpty()) throw new UserNotFound();

        verifyPriceVariationMargin(CryptoCurrencySymbol.valueOf(request.getCrypto_symbol()), request.getPrice());
        TransactionIntention transactionIntention =
                TransactionIntention.builder()
                        .type(OperationType.valueOf(request.getOperation_type()))
                        .creationDate(LocalDateTime.now())
                        .creator(userResult.get())
                        .cryptoSymbol(CryptoCurrencySymbol.valueOf((request.getCrypto_symbol())))
                        .cryptoPrice(request.getPrice())
                        .final_price(exchangeService.convertToArs(request.getPrice()* request.getAmount()))
                        .cryptoAmount(request.getAmount())
                        .build();

        return transactionIntentionRepository.save(transactionIntention);
    }

    @Override
    public List<TransactionIntention> getAllTransactionIntentions() {
        var transationIntetionsIterable = transactionIntentionRepository.findAll();
        return  StreamSupport.stream(transationIntetionsIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private void verifyPriceVariationMargin(CryptoCurrencySymbol cryptoCurrencySymbol, Double price) {
        var currency = cryptoService.getCurrencyBySymbol(cryptoCurrencySymbol);
        var price_variation = currency.getPrice() * 0.05;
        var min_price_variation = currency.getPrice() - price_variation;
        var max_price_variation = currency.getPrice() + price_variation;

        if (price < min_price_variation || price > max_price_variation ) throw new PriceVariationMarginConflict();
    }
}
