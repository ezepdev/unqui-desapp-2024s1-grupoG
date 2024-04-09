package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.services;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.persistence.entities.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.persistence.repositories.AllUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AllUsers users;
    private final JWTService jwtService;

    public void registerUser(String username, String password) {
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        users.save(user);
        
    }
}
