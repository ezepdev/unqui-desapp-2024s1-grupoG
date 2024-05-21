package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.ExecuteTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ITransactionIntentionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TransactionRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
                                        .creator_operations_amount(transactionIntention.getCreator().getOperationsAmount())
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
                .transaction_state(transactionIntention.getState().name())
                .build();

        return ResponseEntity.ok(createTransactionIntentionResponse);
    }

    @PostMapping("{id}")
    public ResponseEntity<ExecuteTransactionIntentionResponse> executeTransactionIntention(Integer id, @RequestBody ExecuteTransactionIntentionRequest request) {
        var created_transaction_id = transactionService.executeTransactionIntention(id,request);
        return ResponseEntity.created(URI.create("/transactions/" + created_transaction_id)).build();
    }
}