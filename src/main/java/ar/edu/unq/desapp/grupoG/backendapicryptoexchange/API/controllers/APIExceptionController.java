package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.ErrorResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.BadRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRegisterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleRegisterException(BadRegisterException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(HttpStatus.BAD_REQUEST).Description(ex.getMessage()
        ).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
}