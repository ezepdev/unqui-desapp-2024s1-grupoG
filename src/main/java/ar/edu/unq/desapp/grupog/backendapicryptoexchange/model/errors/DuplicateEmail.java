package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


public class DuplicateEmail extends Error {


    public DuplicateEmail() {
        super("Email already in use","The email already exists. Please check the email","EMAIL_ALREADY_IN_USE");
    }

}
