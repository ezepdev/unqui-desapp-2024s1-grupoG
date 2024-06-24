package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


public class InvalidCredentials extends Error {
    public InvalidCredentials() {
        super("Invalid credentials","The email or password are invalid","INVALID_CREDENTIALS");
    }
}
