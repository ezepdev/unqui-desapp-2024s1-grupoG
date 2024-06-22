package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error extends RuntimeException{
    private String message;
    private String description;
    private String code;
}
