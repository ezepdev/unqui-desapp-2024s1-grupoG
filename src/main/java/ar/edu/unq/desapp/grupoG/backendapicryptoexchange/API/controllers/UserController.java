package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.Mapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.TransactionMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.UserMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction.TransactionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.Transaction;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ITransactionService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;
    ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new UserMapper().mapToUserResponses(users));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByUser(@PathVariable Long id, @RequestParam LocalDate from_date, @RequestParam LocalDate to_date) {
        {
            List<Transaction> transactions = transactionService.getTransactionsByUserBetweenDates(id, from_date, to_date);

            return ResponseEntity.ok(new Mapper<Transaction, TransactionResponse>().mapTo(transactions, TransactionMapper::mapToTransactionResponse));
        }

        }
    }
