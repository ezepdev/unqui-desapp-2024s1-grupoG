package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

import lombok.AllArgsConstructor;

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


