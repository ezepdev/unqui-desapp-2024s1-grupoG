package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest{
        @Size(min = 3,max = 30, message = "El campo nombre debe contener entre 3 y 30 caracteres")
        @NotBlank
        private final String name;
        @Size(min = 3,max = 30 ,message = "El campo nombre debe contener entre 3 y 30 caracteres")
        @NotBlank
        private final String surname;
        @Email( message = "El campo email debe tener un formato de email")
        @NotBlank
        private final String email;
        @Size(min=10,max=30, message ="El campo direccion debe contener entre 10 y 30 caracteres")
        @NotBlank
        private final String address;
        @NotBlank
        @NotNull
        @Size(min = 22, max = 22, message = "El campo CVU debe contener 22 caracteres")
        @Pattern(regexp = "\\d{22}", message = "EL campo CVU debe contener solo digitos")
        private final String cvu;
        @NotNull
        @NotBlank
        @Size(min = 8, max = 8, message = "El campo Wallet address debe contener 8 caracteres")
        @Pattern(regexp = "\\d{8}", message = "El campo Wallet address debe contener solo digitos")
        @NotNull
        private final String wallet_address;
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")
        private final String password;
}
