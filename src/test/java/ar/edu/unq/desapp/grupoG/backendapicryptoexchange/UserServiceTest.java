package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.EmailAlreadyInUseError;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private AuthService authService;
    @MockBean
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Restablece los mocks antes de cada prueba
        reset(userRepository);
    }

    @Test
    public void testRegisterUser() {
        RegisterRequest request = new RegisterRequest("Jose", "AMALFITANI", "JOSE.EMAIL@GMAIL.COM", "ADDRESS", "123456789123456789123", "12345678", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .surname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();

        // Simula el comportamiento del repositorio
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Ejecuta el método bajo prueba
        User result = authService.registerUser(request);

        // Verifica el resultado
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterUserWithEmailAlreadyInUse() {
        RegisterRequest request = new RegisterRequest("Jose", "AMALFITANI", "JOSE.EMAIL@GMAIL.COM", "ADDRESS", "123456789123456789123", "12345678", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .surname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();
        // Simula el comportamiento del repositorio
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        // Verifica que se lance la excepción esperada
        assertThrows(EmailAlreadyInUseError.class, () -> authService.registerUser(request));
    }

    @Test
    public void testLoginUser() {
        LoginRequest request = new LoginRequest("JOSE.EMAIL@GMAIL.COM", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .surname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();

        // Simula el comportamiento del repositorio
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        // Ejecuta el método bajo prueba
        User result = authService.loginUser(request);

        // Verifica el resultado
        assertEquals(user, result);
    }

    @Test
    public void testLoginUserNotFound() {
        LoginRequest request = new LoginRequest("JOSE.EMAIL@GMAIL.COM", "Pepe1234!");

        // Simula el comportamiento del repositorio
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Verifica que se lance la excepción esperada
        assertThrows(UserNotFound.class, () -> authService.loginUser(request));
    }
}
