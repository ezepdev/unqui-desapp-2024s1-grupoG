package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionIntentionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    ITransactionService transactionService;

    @PostMapping
    @RequestMapping("/execute/{Id}")
    public ResponseEntity<Transaction> executeTransaction(int Id,TransactionIntentionRequest request) {
        var createdTransaction = transactionService.executeTransactionOffer(Id,request);
        return ResponseEntity.ok(createdTransaction);
    }
}