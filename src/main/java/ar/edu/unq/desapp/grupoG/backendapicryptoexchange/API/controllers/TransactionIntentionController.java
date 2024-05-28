package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mappers;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionIntentionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction_intentions")
@RequiredArgsConstructor
@Tag(name = "TransactionIntentions", description = "TransactionIntentions APIs")

public class TransactionIntentionController {

    private final ITransactionIntentionService transactionService;
    @Operation(
            summary = "Retrieve all active intentions",
            description = "Get all active intentions  with status ACTIVE",
            tags = { "transaction_intentions", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TransactionIntentionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<TransactionIntentionResponse>> getActiveTransactionIntentions(){
        List<TransactionIntention> transactionIntentions = transactionService.getActiveIntentions();

        return ResponseEntity.ok(
                new Mapper<TransactionIntention, TransactionIntentionResponse>()
                        .mapTo(transactionIntentions, Mappers::mapToTransactionIntentionResponse));
    }

    @Operation(
            summary = "Create a new transaction intention",
            description = "Create a new transaction intention",
            tags = { "transaction_intentions", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CreateTransactionIntentionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<CreateTransactionIntentionResponse> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {

        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);

        CreateTransactionIntentionResponse response = new Mapper<TransactionIntention,CreateTransactionIntentionResponse>().mapTo(transactionIntention,Mappers::mapToCreateTransactionIntentionResponse);

        return ResponseEntity.ok(response);
    }


}