package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mappers;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction_intentions")
@RequiredArgsConstructor
public class TransactionIntentionController {

    private final ITransactionIntentionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionIntentionResponse>> getActiveTransactionIntentions(){
        List<TransactionIntention> transactionIntentions = transactionService.getActiveIntentions();

        return ResponseEntity.ok(
                new Mapper<TransactionIntention, TransactionIntentionResponse>()
                        .mapTo(transactionIntentions, Mappers::mapToTransactionIntentionResponse));
    }

    @PostMapping
    public ResponseEntity<CreateTransactionIntentionResponse> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {

        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);

        CreateTransactionIntentionResponse response = new Mapper<TransactionIntention,CreateTransactionIntentionResponse>().mapTo(transactionIntention,Mappers::mapToCreateTransactionIntentionResponse);

        return ResponseEntity.ok(response);
    }


}