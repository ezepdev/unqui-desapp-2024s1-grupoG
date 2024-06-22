package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.CreateTransactionIntentionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transactionintention.TransactionIntentionResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappersa.Mapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappersa.Mappers;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionIntention;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionIntentionService;
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
            description = "Get all active intentions  with status ACTIVE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TransactionIntentionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",description = "Transaction intention not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<TransactionIntentionResponse>> getActiveTransactionIntentions(){
        List<TransactionIntention> transactionIntentions = transactionService.getActiveIntentions();

        return ResponseEntity.ok(
                new Mapper<TransactionIntention, TransactionIntentionResponse>()
                        .mapTo(transactionIntentions, Mappers::mapToTransactionIntentionResponse));
    }

    @Operation(
            summary = "Create a new transaction intention",
            description = "Create a new transaction intention"
            )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CreateTransactionIntentionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid data",content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<CreateTransactionIntentionResponse> createTransactionIntention(@RequestBody CreateTransactionIntentionRequest request) {

        TransactionIntention transactionIntention = transactionService.createTransactionIntention(request);

        CreateTransactionIntentionResponse response = new Mapper<TransactionIntention,CreateTransactionIntentionResponse>().mapTo(transactionIntention,Mappers::mapToCreateTransactionIntentionResponse);

        return ResponseEntity.ok(response);
    }


}