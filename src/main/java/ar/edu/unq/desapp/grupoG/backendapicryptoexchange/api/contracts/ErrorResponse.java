package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts;


public record ErrorResponse (
    String code,
    String message,
    String description
){};
