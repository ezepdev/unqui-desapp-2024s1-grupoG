package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

import lombok.Builder;

public class InvalidTransaction extends Error {

    @Builder
    InvalidTransaction(String message, String description, String code) {
        super(message,description,code);
    }

    public final String getMessage(){
        return "Invalid transaction";
    }

    public final String getCode(){
        return "INVALID_TRANSACTION";
    }

}
