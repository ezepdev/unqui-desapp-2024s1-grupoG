package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFound extends Error {
    @Override
    public final String getMessage(){
        return "User not found";
    }
    @Override
    public final String getDescription(){
        return "The user does not exist. Please check the user identifier";
    }
    @Override
    public final String getCode(){
        return "USER_NOT_FOUND";
    }
}


