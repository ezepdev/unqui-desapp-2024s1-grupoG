package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.EmailAlreadyInUseError;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.InvalidDataException;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository users;

    public User registerUser(RegisterRequest request) {
        
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .password(request.getPassword())
                .email(request.getEmail())
                .walletAddress(request.getWallet_address())
                .cvu(request.getCvu())
                .address(request.getAddress())
                .build();

        if (users.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseError();
        }

        users.save(user);
        return user;
        
    }

    public User loginUser(LoginRequest request) {
        Optional<User> response = users.findByEmail(request.getEmail());

        if (response.isPresent()) {
            if (Objects.equals(response.get().getPassword(), request.getPassword())) {
                return response.get();
            }
            else {
                throw new InvalidDataException();
            }
        }
        else {
            throw new InvalidDataException();
        }
    }
}
