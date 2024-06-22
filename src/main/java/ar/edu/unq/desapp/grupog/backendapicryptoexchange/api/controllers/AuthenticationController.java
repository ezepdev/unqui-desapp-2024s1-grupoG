package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.AuthenticationResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IAuthService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.common.AuthenticationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthenticationController {

    private static final Logger log = LogManager.getLogger(AuthenticationController.class);


    @Autowired
    private final IAuthService authService;

    @PostMapping("/register")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class)) }),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content) })
    @Operation(summary = "Register a new user", description = "Register a new user")
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

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Email or password is invalid",
            content = @Content) })
    @Operation(summary = "Login a user", description = "Login a user")
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
