package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error extends RuntimeException{
    private String message;
    private String description;
    private String code;
}
