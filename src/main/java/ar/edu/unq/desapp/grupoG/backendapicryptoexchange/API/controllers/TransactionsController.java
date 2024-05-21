package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.UpdateTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.StartTransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    @Autowired
    ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<StartTransactionResponse> startTransaction(@RequestBody StartTransactionRequest request) {
        var created_transaction = transactionService.startTransaction(request);
        var uri_created_transaction = URI.create("/transactions/" + created_transaction.getId());
        return ResponseEntity.created(uri_created_transaction)
                .body(
                        StartTransactionResponse.builder()
                                .created_transaction_id(created_transaction.getId())
                                .URI(uri_created_transaction.toString()).build());
    }

    @PatchMapping("/{transaction_id}")
    public ResponseEntity<Transaction> updateTransactionStatus(@PathVariable Integer transaction_id, @RequestBody UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransactionStatus(transaction_id,request);

        return ResponseEntity.ok().body
                (transaction);
    }
}