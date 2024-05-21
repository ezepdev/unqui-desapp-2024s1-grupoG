package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.ErrorResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.*;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class APIExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRegisterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleRegisterException(BadRegisterException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(HttpStatus.BAD_REQUEST).description(ex.getMessage()
        ).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyInUseError.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseConflict(EmailAlreadyInUseError ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(HttpStatus.CONFLICT).description(ex.getDescription()
        ).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFound ex) {
            return new ResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(),ex.getDescription()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PriceVariationMarginConflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handlePriceVariationMarginConflict(PriceVariationMarginConflict ex) {
        return new ResponseEntity(new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage(),ex.getDescription()), HttpStatus.CONFLICT);
    }
}