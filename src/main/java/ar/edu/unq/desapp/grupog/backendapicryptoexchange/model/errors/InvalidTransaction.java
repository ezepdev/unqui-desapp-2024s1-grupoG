package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

import lombok.Builder;

public class InvalidTransaction extends Error {

    @Builder
    InvalidTransaction(String message, String description, String code) {
        super(message,description,code);
    }

    @Override
    public final String getMessage(){
        return "Invalid transaction";
    }

    @Override
    public final String getCode(){
        return "INVALID_TRANSACTION";
    }

}
