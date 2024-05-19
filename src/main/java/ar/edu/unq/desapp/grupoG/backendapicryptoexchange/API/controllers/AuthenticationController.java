package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.BadRegisterException;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IAuthService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.AuthService;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request) throws BadRegisterException {
        User user_registered = authService.registerUser(request);

        return ResponseEntity.ok(
                new RegisterResponse(
                        user_registered.getName(),
                        user_registered.getSurname(),
                        user_registered.getEmail(),
                        user_registered.getAddress(),
                        user_registered.getCvu(),
                        user_registered.getWalletAddress(),
                        user_registered.getPassword()
                )
        );
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        User user = authService.loginUser(request);

        if (user != null) {
            return ResponseEntity.ok(
                    LoginResponse.builder()
                            .name(user.getName())
                            .email(user.getEmail())
                            .address(user.getAddress())
                            .surname(user.getSurname())
                            .wallet_address(user.getWalletAddress())
                            .cvu(user.getCvu())
                            .build()
            );
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
