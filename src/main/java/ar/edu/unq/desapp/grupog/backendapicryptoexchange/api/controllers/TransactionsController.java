package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction.*;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers.Mapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers.TransactionMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.TradedVolume;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Transactions APIs")

public class TransactionsController {

    private final ITransactionService transactionService;

    @Operation(
            summary = "get traded volume for a user",
            description = "Get traded volume for a user. The traded volume is the sum of all transactions of a user between two dates")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traded volume retrieved successfully", content = { @Content(schema = @Schema(implementation = TradedVolume.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "User not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping()
    public ResponseEntity<List<TradedVolume>> getTransactionsByUser(@RequestBody TransactionsByUserRequest request, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        {
            List<TradedVolume> transactions = transactionService.getTransactionsByUserBetweenDates(request.user_id(), fromDate, toDate);
            return ResponseEntity.ok(transactions);
        }

    }

    @Operation(
            summary = "start a transaction",
            description = "Start a transaction. The transaction is a transfer of cryptos from the user who initiated the transaction to the user who created the intention"
            )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transaction started successfully", content = { @Content(schema = @Schema(implementation = StartTransactionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<StartTransactionResponse> startTransaction(@RequestBody StartTransactionRequest request) {
        var createdTransaction = transactionService.startTransaction(request);
        var uri_created_transaction = URI.create("/transactions/" + createdTransaction.getId());
        return ResponseEntity.created(uri_created_transaction)
                .body(
                        new StartTransactionResponse(
                                createdTransaction.getId(),
                                "Transaction started successfully",
                                uri_created_transaction.toString())
                );
    }

    @Operation(
            summary = "update status of a transaction",
            description = "Update status of a transaction.The transaction is a transfer of cryptos from the user who initiated the transaction to the user who created the intention"
            )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transaction updated successfully", content = { @Content(schema = @Schema(implementation = TransactionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PatchMapping("/{transaction_id}")
    public ResponseEntity<TransactionResponse> updateTransactionStatus(@PathVariable Integer transactionId, @RequestBody UpdateTransactionRequest request) {
        var transaction = transactionService.updateTransactionStatus(transactionId,request);
        TransactionResponse response = new Mapper<Transaction, TransactionResponse>().mapTo(transaction, TransactionMapper::mapToTransactionResponse);
        return ResponseEntity.ok(response);
    }
}