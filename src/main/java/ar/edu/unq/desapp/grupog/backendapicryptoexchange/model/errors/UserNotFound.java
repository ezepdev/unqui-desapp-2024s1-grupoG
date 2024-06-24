package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class UserNotFound extends Error {
    public UserNotFound() {
        super("User not found","The user does not exist. Please check the user identifier", "USER_NOT_FOUND"); }
}


