package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginRequest {
    @NotEmpty
    @Email( message = "El campo email debe tener un formato de email" )
    private String email;
    @NotEmpty
    private String password;
}
