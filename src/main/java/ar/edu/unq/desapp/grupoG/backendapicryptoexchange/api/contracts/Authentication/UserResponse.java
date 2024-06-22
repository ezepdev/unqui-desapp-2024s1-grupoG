package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Authentication;

public record UserResponse(Long id,
String full_name,
String email,
String address,
String cvu,
String wallet_address
){}
