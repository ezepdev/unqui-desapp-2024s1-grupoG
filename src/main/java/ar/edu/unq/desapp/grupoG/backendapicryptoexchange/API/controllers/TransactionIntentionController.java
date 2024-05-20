package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionOffer.CreateTransactionIntentionRequest;
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
    public ResponseEntity<TransactionIntention> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {
        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);
        return ResponseEntity.ok(transactionIntention);
    }
}