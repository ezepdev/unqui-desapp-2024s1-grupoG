package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class EmailAlreadyInUseError extends Error {


    public EmailAlreadyInUseError() {
        super("Email already in use","The email already exists. Please check the email","EMAIL_ALREADY_IN_USE");
    }

}
