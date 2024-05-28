package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.TransactionMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    @Autowired
    ITransactionService transactionService;

    @GetMapping()
    public ResponseEntity<List<TradedVolume>> getTransactionsByUser(@RequestBody TransactionsByUserRequest request, @RequestParam LocalDate from_date, @RequestParam LocalDate to_date) {
        {
            List<TradedVolume> transactions = transactionService.getTransactionsByUserBetweenDates(request.user_id(), from_date, to_date);
            return ResponseEntity.ok(transactions);
        }

    }

    @PostMapping
    public ResponseEntity<StartTransactionResponse> startTransaction(@RequestBody StartTransactionRequest request) {
        var created_transaction = transactionService.startTransaction(request);
        var uri_created_transaction = URI.create("/transactions/" + created_transaction.getId());
        return ResponseEntity.created(uri_created_transaction)
                .body(
                        new StartTransactionResponse(
                                created_transaction.getId(),
                                "Transaction started successfully",
                                uri_created_transaction.toString())
                );
    }

    @PatchMapping("/{transaction_id}")
    public ResponseEntity<TransactionResponse> updateTransactionStatus(@PathVariable Integer transaction_id, @RequestBody UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransactionStatus(transaction_id,request);
        TransactionResponse response = new Mapper<Transaction, TransactionResponse>().mapTo(transaction, TransactionMapper::mapToTransactionResponse);
        return ResponseEntity.ok(response);
    }
}