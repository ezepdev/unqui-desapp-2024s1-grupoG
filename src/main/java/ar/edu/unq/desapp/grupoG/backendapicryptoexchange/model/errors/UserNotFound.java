package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class UserNotFound extends Error {
    public final String getMessage(){
        return "User not found";
    }
    public final String getDescription(){
        return "The user does not exist. Please check the user identifier";
    }
    public final String getCode(){
        return "USER_NOT_FOUND";
    }
}


