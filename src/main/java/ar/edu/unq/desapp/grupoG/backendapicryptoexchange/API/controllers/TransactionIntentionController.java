package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction_intentions")
@RequiredArgsConstructor
public class TransactionIntentionController {

    private final ITransactionIntentionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionIntentionResponse>> getAllTransactionIntentions() {
        var transactionIntentions = transactionService.getAllTransactionIntentions();

        return ResponseEntity.ok(
                transactionIntentions.stream().map(
                        transactionIntention ->
                                TransactionIntentionResponse.builder()
                                        .transaction_intention_id(transactionIntention.getId())
                                        .operation_type(transactionIntention.getType().name())
                                        .crypto_symbol(transactionIntention.getCryptoSymbol().name())
                                        .price(transactionIntention.getCryptoPrice())
                                        .amount(transactionIntention.getCryptoAmount())
                                        .final_price(Currency.builder().value(transactionIntention.getFinal_price()).currency_symbol("ARS").build())
                                        .creator_id(transactionIntention.getCreator().getId())
                                        .creation_date(transactionIntention.getCreationDate())
                                        .reputation(transactionIntention.getCreator().get_reputation())
                                        .build())
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CreateTransactionIntentionResponse> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {

        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);


        CreateTransactionIntentionResponse createTransactionIntentionResponse = CreateTransactionIntentionResponse.builder()
                .transaction_intention_id(transactionIntention.getId())
                .operation_type(transactionIntention.getType().name())
                .crypto_symbol(transactionIntention.getCryptoSymbol().name())
                .price(transactionIntention.getCryptoPrice())
                .amount(transactionIntention.getCryptoAmount())
                .final_price(Currency.builder().value(transactionIntention.getFinal_price()).currency_symbol("ARS").build())
                .creator_id(transactionIntention.getCreator().getId())
                .creation_date(transactionIntention.getCreationDate())
                .transaction_intention_state(transactionIntention.getState().name())
                .build();

        return ResponseEntity.ok(createTransactionIntentionResponse);
    }


}