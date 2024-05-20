package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction_intentions")
@RequiredArgsConstructor
public class TransactionIntentionController {

    @Autowired
    ITransactionIntentionService transactionService;

    @PostMapping
    public ResponseEntity<CreateTransactionIntentionResponse> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {

        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);

        CreateTransactionIntentionResponse createTransactionIntentionResponse = CreateTransactionIntentionResponse.builder()
                .transaction_intention_id(transactionIntention.getId())
                .operation_type(transactionIntention.getType().name())
                .crypto_symbol(transactionIntention.getCryptoSymbol().name())
                .price(transactionIntention.getCryptoPrice())
                .amount(transactionIntention.getCryptoAmount())
                .final_price(transactionIntention.getCryptoPrice())
                .creator_id(transactionIntention.getCreator().getId())
                .build();

        return ResponseEntity.ok(createTransactionIntentionResponse);
    }
}