package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class PriceVariationMarginConflict extends Error {
    public final String getMessage(){
        return "Price variation margin conflict";
    }
    public final String getDescription(){
        return "The price variation margin conflict. The price variation margin is greater or minor than 5%";
    }
    public final String getCode(){
        return "PRICE_VARIATION_MARGIN_CONFLICT";
    }

}