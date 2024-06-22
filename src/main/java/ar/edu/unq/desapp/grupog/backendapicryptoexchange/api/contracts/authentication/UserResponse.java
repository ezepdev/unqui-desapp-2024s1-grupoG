package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication;

public record UserResponse(Long id,
String full_name,
String email,
String address,
String cvu,
String wallet_address
){}
