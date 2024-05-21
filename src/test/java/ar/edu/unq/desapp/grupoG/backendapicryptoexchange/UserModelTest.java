package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserModelTest {
    static User any_user;

    @BeforeAll
    static void setUp() {
        any_user = User.builder().build();
    }

    @AfterEach
    void clean() {
        any_user = User.builder().build();
    }

    @Test
    void testAnyUserCanAddReputationPoint() {
        Integer points = 30;
        Integer started_points = any_user.getReputationPoints();
        any_user.addPoints(points);
        assertEquals( started_points + 30,any_user.getReputationPoints());
    }

}
