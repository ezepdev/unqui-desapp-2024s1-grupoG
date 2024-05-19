package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class UserNotFound extends Error {
    public String getMessage(){
        return "User not found";
    }
    public String getDescription(){
        return "The user does not exist. Please check the user id";
    }
    public String getCode(){
        return "USER_NOT_FOUND";
    }
}


