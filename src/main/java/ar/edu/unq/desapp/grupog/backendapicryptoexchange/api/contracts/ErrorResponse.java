package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts;


public record ErrorResponse (
    String code,
    String message,
    String description
){}
