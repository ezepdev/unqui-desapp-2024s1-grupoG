package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Error extends RuntimeException{
    private final String message;
    private final String description;
    private final String code;
}
