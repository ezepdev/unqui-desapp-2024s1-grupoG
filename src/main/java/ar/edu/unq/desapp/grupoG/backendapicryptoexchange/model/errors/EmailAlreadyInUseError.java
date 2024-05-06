package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class EmailAlreadyInUseError extends RuntimeException {

    public EmailAlreadyInUseError(){
        super("El email ya esta en uso");
        //this.setStackTrace(new StackTraceElement[0]);
    }
}
