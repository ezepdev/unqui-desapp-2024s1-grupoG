package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Transaction;

public record CryptoCotization(
        String crypto_symbol,
        Long crypto_amount,
        Double current_price,
        Double final_price
) {}
