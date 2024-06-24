package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class PriceVariationMarginConflict extends Error {
    @Override
    public final String getMessage(){
        return "Price variation margin conflict";
    }
    @Override
    public final String getDescription(){
        return "The price variation margin conflict. The price variation margin is greater or minor than 5%";
    }
    @Override
    public final String getCode(){
        return "PRICE_VARIATION_MARGIN_CONFLICT";
    }

}