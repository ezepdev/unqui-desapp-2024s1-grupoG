package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class LoginRequest {
    @NotEmpty
    @Email( message = "El campo email debe tener un formato de email" )
    private String email;
    @NotEmpty
    private String password;
}
