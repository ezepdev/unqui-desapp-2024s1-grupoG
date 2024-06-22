package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.ErrorResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRegisterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleRegisterException(BadRegisterException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), ex.getMessage(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(TransactionIntentionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleTransactionIntentionNotFound(TransactionIntentionNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleTransactionNotFound(TransactionNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentials ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateEmail.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseConflict(DuplicateEmail ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotAuthorized.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleUserNotAuthorized(UserNotAuthorized ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTransaction.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleInvalidTransaction(InvalidTransaction ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PriceVariationMarginConflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handlePriceVariationMarginConflict(PriceVariationMarginConflict ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UpdateActionNotAllowed.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleUpdateActionNotAllowed(UpdateActionNotAllowed ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTransactionOperation.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleInvalidTransactionOperation(InvalidTransactionOperation ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}