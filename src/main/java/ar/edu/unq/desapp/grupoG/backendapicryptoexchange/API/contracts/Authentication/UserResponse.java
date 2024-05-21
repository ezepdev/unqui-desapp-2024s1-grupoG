package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String full_name;
    private String email;
    private String address;
    private String cvu;
    private String wallet_address;
}
