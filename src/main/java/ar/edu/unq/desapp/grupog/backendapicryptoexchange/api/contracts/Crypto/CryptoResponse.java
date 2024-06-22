package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Crypto;


public record CryptoResponse(
    String symbol,
    Double price,
    String updated_at
){}
