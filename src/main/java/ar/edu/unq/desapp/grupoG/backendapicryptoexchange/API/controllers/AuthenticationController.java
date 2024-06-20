package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.UserMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.AuthenticationResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.BadRegisterException;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IAuthService;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.common.AuthenticationResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthenticationController {

    @Autowired
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResult result = authService.registerUser(request);

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        result.token(),
                        result.fullName(),
                        result.email()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        AuthenticationResult result = authService.loginUser(request);

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        result.token(),
                        result.fullName(),
                        result.email()
                )
        );
    }
}
