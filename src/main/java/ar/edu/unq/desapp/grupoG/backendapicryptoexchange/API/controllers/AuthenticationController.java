package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.BadRegisterException;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IAuthService;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final IAuthService authService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) throws BadRegisterException {
        User user_registered = authService.registerUser(request);

        return ResponseEntity.ok(
                UserResponse.builder()
                        .id(user_registered.getId())
                        .full_name(user_registered.getName() + " " + user_registered.getSurname())
                        .email(user_registered.getEmail())
                        .address(user_registered.getAddress())
                        .wallet_address(String.valueOf(user_registered.getWalletAddress()))
                        .cvu(user_registered.getCvu())
                        .build()
        );
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        User user = authService.loginUser(request);

        return ResponseEntity.ok(UserResponse.builder()
                            .full_name(user.getName() + " " + user.getSurname())
                            .email(user.getEmail())
                            .address(user.getAddress())
                            .wallet_address(user.getWalletAddress())
                            .cvu(user.getCvu())
                            .build());
    }
}
