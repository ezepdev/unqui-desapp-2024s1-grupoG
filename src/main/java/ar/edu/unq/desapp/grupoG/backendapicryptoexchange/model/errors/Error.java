package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

public class Error extends RuntimeException{
    private String message;
    private String description;
    private String code;
}
