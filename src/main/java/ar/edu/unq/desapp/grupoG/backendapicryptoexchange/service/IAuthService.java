package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;

public interface IAuthService {
    User registerUser(RegisterRequest request);
    User loginUser(LoginRequest request);
}
