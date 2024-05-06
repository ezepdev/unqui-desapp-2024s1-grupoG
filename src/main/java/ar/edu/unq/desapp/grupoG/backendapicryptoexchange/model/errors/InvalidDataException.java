package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(){
        super("Los datos no son correctos");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
