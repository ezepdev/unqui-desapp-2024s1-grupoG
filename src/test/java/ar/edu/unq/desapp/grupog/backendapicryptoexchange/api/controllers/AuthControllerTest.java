package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.RegisterRequest;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class AuthControllerTest {

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
                .content("{ \"name\": \"test\", \"surname\": \"test\", \"email\": \"tesT@test.com\", \"password\": \"Password$123\", \"address\": \"USERADDRESS\", \"walletAddress\": \"12345629\", \"cvu\": \"1234567831234567891232\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John\", \"surname\": \"Doe\", \"email\": \"johnDoe@example.com\",\"password\": \"Password$123\",\"address\": \"123 Main St\", \"walletAddress\": \"12335629\", \"cvu\": \"1234567821234547891232\"}"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenValidLoginRequest_thenReturnsUserResponse() throws Exception {
        when(authService.loginUser(any(LoginRequest.class))).thenReturn(new AuthenticationResult("token", "John Doe", "john.doe@example.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"john.doe@example.com\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.full_name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
