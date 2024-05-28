package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mappers;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.PriceVariationMarginConflict;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IExchangeService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TransactionIntentionService implements ITransactionIntentionService {

    private final ITransactionIntentionRepository transactionIntentionRepository;
    private final IExchangeService exchangeService;
    private final ICryptoService cryptoService;
    private final IUserRepository userRepository;
    @Setter(AccessLevel.PRIVATE)
    private static Double price_variation_margin = 0.05;


    public TransactionIntention createTransactionIntention(CreateTransactionIntentionRequest request) {

        //* Verify user exists
        Optional<User> userResult = userRepository.findById(request.intention_creator_id());
        if (userResult.isEmpty()) throw new UserNotFound();

        //* Verify price is within the variation margin
        if (!cryptoService.isAllowedPrice(CryptoCurrencySymbol.valueOf(request.crypto_symbol()),request.crypto_price())) throw new PriceVariationMarginConflict();
        Mapper<CreateTransactionIntentionRequest, TransactionIntention> mapper = new Mapper<>();

        //* Create transaction intention
        TransactionIntention transactionIntention =
                mapper.mapTo(request, Mappers::mapToTransactionIntention);
        transactionIntention.setCreator(userResult.get());
        transactionIntention.setFinalPrice(exchangeService.convertToArs(request.finalPrice().doubleValue()));

        return transactionIntentionRepository.save(transactionIntention);
    }

    @Override
    public List<TransactionIntention> getActiveIntentions() {
        return transactionIntentionRepository.findByState(TransactionIntentionState.ACTIVE);
    }

}
