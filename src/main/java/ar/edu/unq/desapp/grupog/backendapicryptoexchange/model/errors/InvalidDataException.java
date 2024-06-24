package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

public class InvalidDataException extends Error{
    public InvalidDataException(){
        super("Data is invalid","The data is not valid", "INVALID_DATA");
    }
}
