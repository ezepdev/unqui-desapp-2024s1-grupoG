package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers.UserMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.config.SecurityConfig;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IAuthService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @MockBean
    private IUserRepository userRepository;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private User user;
    private User user2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = User.builder().id(1L).email("john.doe@example.com").name("John").lastname("Doe").password("password").address("123 Main St").cvu("1234567890123456789012").walletAddress("wallet1").operationsAmount(0).reputationPoints(0).build();
        user2 = User.builder().id(2L).email("jane.doe@example.com").name("Jane").lastname("Doe").password("password").address("456 Elm St").cvu("1234567890123456789013").walletAddress("wallet2").operationsAmount(0).reputationPoints(0).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> mockUsers = Arrays.asList(user, user2);
        when(userService.getAllUsers()).thenReturn(mockUsers);

        String expectedJson = new ObjectMapper().writeValueAsString(UserMapper.mapToUserResponses(mockUsers));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
//
//    @Test
//    void testGetAllUsers_NotFound() throws Exception {
//
//        when(userService.getAllUsers()).thenThrow(new RuntimeException("User not found"));
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isNotFound());
//    }
}
