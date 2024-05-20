package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class RegisterResponse{
    private String name;
    private String surname;
    private String email;
    private String address;
    private String cvu;
    private int wallet_address;
    private String password;
}
