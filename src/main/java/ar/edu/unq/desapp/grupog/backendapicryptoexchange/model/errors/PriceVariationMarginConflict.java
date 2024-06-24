package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class PriceVariationMarginConflict extends Error {

    public PriceVariationMarginConflict() {
        super("Price variation margin conflict","The price variation margin conflict. The price variation margin is greater or minor than 5%", "PRICE_VARIATION_MARGIN_CONFLICT");
    }
}