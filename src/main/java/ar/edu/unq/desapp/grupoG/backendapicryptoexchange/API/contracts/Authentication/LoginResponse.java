package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
public class LoginResponse{
    private String name;
    private String surname;
    private String email;
    private String address;
    private String cvu;
    private int wallet_address;
}
