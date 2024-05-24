package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class BadRegisterException extends Error{
    public BadRegisterException() {
        super("Email already in use","The email already exists. Please check the email","EMAIL_ALREADY_IN_USE");
    }
}
