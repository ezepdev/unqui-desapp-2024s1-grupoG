package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.mappers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    @Test
    void mapToUserResponses() {
        User user = User.builder().email("JOSE.EMAIL@GMAIL.COM").build();
        User user2 = User.builder().email("JUAN.EMAIL@GMAIL.COM").build();
        var usersResponse = UserMapper.mapToUserResponses(List.of(user, user2));
        assertEquals(2, usersResponse.size());
        assertEquals(user.getEmail(), usersResponse.get(0).email());
        assertEquals(user2.getEmail(), usersResponse.get(1).email());

    }
}
