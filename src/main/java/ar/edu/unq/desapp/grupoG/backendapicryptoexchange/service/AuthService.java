package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.AllUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AllUsers users;

    public User registerUser(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .password(request.getPassword())
                .email(request.getEmail())
                .wallet_address(request.getWallet_address())
                .cvu(request.getCvu())
                .address(request.getAddress())
                .build();

        users.save(user);

        System.out.println(users.findAll());

        return user;
        
    }

    public User loginUser(LoginRequest request) {
        Optional<User> response = users.findByEmail(request.email());

        if (response.isPresent()) {
            if (Objects.equals(response.get().getPassword(), request.password())) {
                return response.get();
            }
            else {
                throw new Error("Password incorrecto");
            }
        }
        else {
            throw new Error(" Usuario no encontrado");
        }

    }
}
