package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class BadRegisterException extends RuntimeException{

    // private static final long serialVersionUID = 1L; TODO : preguntarle al profe
    public BadRegisterException(){
        super("Los datos son invalidos");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
