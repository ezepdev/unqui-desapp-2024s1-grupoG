package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.DuplicateEmail;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.InvalidCredentials;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.JWTService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.common.AuthenticationResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")

class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private JWTService jwtService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthenticationManager authManager;
    @Test
    void testRegisterUser() {
        User user = User.builder()
                .name("Test")
                .lastname("User")
                .email("test@example.com")
                .walletAddress("walletAddress")
                .cvu("cvu")
                .address("address")
                .password("password")
                .build();


        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("testToken");
        when(userRepository.save(any(User.class))).thenReturn(user);

        RegisterRequest request = new RegisterRequest("Test","User","test@example.com","address","cvu","walletAddress","password");

        AuthenticationResult result = authService.registerUser(request);

        assertNotNull(result);
        assertEquals("testToken", result.token());
        assertEquals("Test User", result.fullName());
        assertEquals("test@example.com", result.email());

    }
    @Test
    void testRegisterUserWithExistUser() {
        User user = User.builder()
                .name("Test")
                .lastname("User")
                .email("test@example.com")
                .walletAddress("walletAddress")
                .cvu("cvu")
                .address("address")
                .password("password")
                .build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("testToken");

        RegisterRequest request = new RegisterRequest("Test","User","test@example.com","address","cvu","walletAddress","password");

        assertThrows(DuplicateEmail.class, () -> authService.registerUser(request));
    }

    @Test
    void testLoginUser() {
        User user = User.builder()
                .name("Test")
                .lastname("User")
                .email("test@example.com")
                .walletAddress("walletAddress")
                .cvu("cvu")
                .address("address")
                .password("password")
                .build();


        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("testToken");

        LoginRequest request = new LoginRequest("test@example.com","password");

        AuthenticationResult result = authService.loginUser(request);

        assertNotNull(result);
    }
    @Test
    void testLoginUserWithInvalidCredentials() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(jwtService.generateToken(any(User.class))).thenReturn("testToken");

        LoginRequest request = new LoginRequest("test@example.com","password");

        assertThrows(InvalidCredentials.class, () -> authService.loginUser(request));
    }
}