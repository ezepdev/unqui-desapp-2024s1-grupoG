package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.crypto;


public record CryptoResponse(
    String symbol,
    Double price,
    String updated_at
){}
