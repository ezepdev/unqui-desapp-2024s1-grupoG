package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.JWTServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class JWTServiceTest {

    @Autowired
    private JWTServiceImpl jwtService;

    @MockBean
    private UserDetails userDetails;

    @Value("${security.jwt.secret-key}")
    private String secretKey = "my-secret-key";

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration = 1000 * 60 * 60; // 1 hour

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService.secretKey = secretKey;
        jwtService.jwtExpiration = jwtExpiration;
    }

    @Test
    void testGenerateToken() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
    }

    @Test
    void testIsTokenValid() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testIsTokenInvalid() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);

        when(userDetails.getUsername()).thenReturn("otherUser");

        assertFalse(jwtService.isTokenValid(token, userDetails));
    }


    @Test
    void testExtractUserName() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);

        String extractedUsername = jwtService.extractUserName(token);

        assertEquals("testUser", extractedUsername);
    }

    @Test
    void testTokenExpiration() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);

        assertFalse(jwtService.isTokenExpired(token));
    }

    @Test
    void testTokenExpired() throws InterruptedException {
        when(userDetails.getUsername()).thenReturn("testUser");

        jwtService.jwtExpiration = 1000; // 1 second for test

        String token = jwtService.generateToken(userDetails);

        Thread.sleep(2000); // wait for the token to expire

        assertThrows(ExpiredJwtException.class, () -> {
            jwtService.isTokenValid(token, userDetails);
        });
    }
}
