package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Crypto;


public record CryptoResponse(
    String symbol,
    Double price,
    String updated_at
){}
