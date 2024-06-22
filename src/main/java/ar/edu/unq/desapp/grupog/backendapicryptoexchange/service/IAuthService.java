package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.common.AuthenticationResult;


public interface IAuthService {
    AuthenticationResult registerUser(RegisterRequest request);
    AuthenticationResult loginUser(LoginRequest request);
}
