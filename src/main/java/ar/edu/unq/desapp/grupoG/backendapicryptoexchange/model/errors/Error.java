package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Error extends RuntimeException{
    private String message;
    private String description;
    private String code;
}
