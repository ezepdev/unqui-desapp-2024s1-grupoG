package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.common.AuthenticationResult;


public interface IAuthService {
    AuthenticationResult registerUser(RegisterRequest request);
    AuthenticationResult loginUser(LoginRequest request);
}
