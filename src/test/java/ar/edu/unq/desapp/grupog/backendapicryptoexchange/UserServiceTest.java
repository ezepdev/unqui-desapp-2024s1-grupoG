package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.DuplicateEmail;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.Impl.AuthService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.Impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @MockBean
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {

        reset(userRepository);
    }

 /*   @Test
    public void testRegisterUser() {
        RegisterRequest request = new RegisterRequest("Jose", "AMALFITANI", "JOSE.EMAIL@GMAIL.COM", "ADDRESS", "123456789123456789123", "12345678", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .lastname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();


        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);


        AuthenticationResult result = authService.registerUser(request);


        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
*/
    @Test
    public void testRegisterUserWithEmailAlreadyInUse() {
        RegisterRequest request = new RegisterRequest("Jose", "AMALFITANI", "JOSE.EMAIL@GMAIL.COM", "ADDRESS", "123456789123456789123", "12345678", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .lastname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));


        assertThrows(DuplicateEmail.class, () -> authService.registerUser(request));
    }

 /*   @Test
    public void testLoginUser() {
        LoginRequest request = new LoginRequest("JOSE.EMAIL@GMAIL.COM", "Pepe1234!");
        User user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .lastname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();


        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));


        AuthenticationResult result = authService.loginUser(request);


        assertEquals(user, result);
    }*/
//
//    @Test
//    public void testLoginUserNotFound() {
//        LoginRequest request = new LoginRequest("JOSE.EMAIL@GMAIL.COM", "Pepe1234!");
//
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
//
//
//        assertThrows(InvalidCredentials.class, () -> authService.loginUser(request));
//    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
        assertThat(result).contains(user1, user2);
    }
}
