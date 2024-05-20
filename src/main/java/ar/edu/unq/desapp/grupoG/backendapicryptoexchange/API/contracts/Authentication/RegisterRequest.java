package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class RegisterRequest{
        @Size(min = 3,max = 30, message = "El campo nombre debe contener entre 3 y 30 caracteres")
        @NotEmpty
        private String name;
        @Size(min = 3,max = 30 ,message = "El campo nombre debe contener entre 3 y 30 caracteres")
        @NotEmpty
        private String surname;
        @Email( message = "El campo email debe tener un formato de email")
        @NotEmpty
        private String email;
        @Size(min=10,max=30, message ="El campo direccion debe contener entre 10 y 30 caracteres")
        @NotEmpty
        private String address;
        // todo : add cvu constraint
        private String cvu;
        @Digits(integer=8,fraction = 0, message ="El campo direccion de billetera debe tener exactamente 8 digitos")
        @NotNull
        private int wallet_address;
        @NotEmpty
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")
        private String password;
}
