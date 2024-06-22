package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.transaction;

public record CryptoCotization(
        String crypto_symbol,
        Long crypto_amount,
        Double current_price,
        Double final_price
) {}
