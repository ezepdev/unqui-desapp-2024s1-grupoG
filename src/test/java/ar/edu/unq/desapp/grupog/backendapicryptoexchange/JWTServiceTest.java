package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;

    @Test
    void testGenerateToken() {

    }

}
