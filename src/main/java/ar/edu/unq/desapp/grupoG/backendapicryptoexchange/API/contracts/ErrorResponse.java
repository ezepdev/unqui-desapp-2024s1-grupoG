package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;


public record ErrorResponse (
    String code,
    String message,
    String description
){};
