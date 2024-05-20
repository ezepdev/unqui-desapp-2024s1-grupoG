package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class EmailAlreadyInUseError extends Error {
    public final String getMessage(){
        return "Email already in use";
    }
    public final String getDescription(){
        return "The email already exists. Please check the email";
    }
    public final String getCode(){
        return "EMAIL_ALREADY_IN_USE";
    }

}
