package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IAuthService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.common.AuthenticationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest(controllers = AuthenticationController.class)
//@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private IAuthService authService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        registerRequest = new RegisterRequest("Jose", "AMALFITANI", "JOSE.EMAIL@GMAIL.COM", "ADDRESS", "123456789123456789123", "12345678", "Pepe1234!");
        loginRequest = new LoginRequest("JOSE.EMAIL@GMAIL.COM", "Pepe1234!");
        user = User.builder()
                .id(1L)
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .lastname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("Pepe1234!")
                .cvu("123456789123456789123")
                .build();
    }

    @Test
    void whenValidRegisterRequest_thenReturnsUserResponse() throws Exception {
        when(authService.registerUser(any(RegisterRequest.class))) .thenReturn(new AuthenticationResult("token", "full_name", "email"));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .content("{ \"name\": \"test\", \"surname\": \"test\", \"email\": \"test@test.com\", \"password\": \"Password123!\", \"address\": \"addressssssssssss\", \"wallet_address\": \"12345678\", \"cvu\": \"1234567891234567891232\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"John\", \"surname\": \"Doe\", \"email\": \"john.doe@example.com\", \"address\": \"123 Main St\", \"walletAddress\": \"1234567890\", \"cvu\": \"0987654321\"}"));
//        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
//
//    @Test
//    void whenValidLoginRequest_thenReturnsUserResponse() throws Exception {
//        when(authService.loginUser(any(LoginRequest.class))).thenReturn(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"email\": \"john.doe@example.com\", \"password\": \"password\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.full_name").value("John Doe"))
//                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
//                .andExpect(jsonPath("$.address").value("123 Main St"))
//                .andExpect(jsonPath("$.wallet_address").value("1234567890"))
//                .andExpect(jsonPath("$.cvu").value("0987654321"));
//    }
}
