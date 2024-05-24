package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public record UserResponse(Long id,
String full_name,
String email,
String address,
String cvu,
String wallet_address
){}
