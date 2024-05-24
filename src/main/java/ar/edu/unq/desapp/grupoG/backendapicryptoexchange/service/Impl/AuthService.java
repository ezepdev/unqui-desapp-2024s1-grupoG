package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.DuplicateEmail;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.InvalidCredentials;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;

    public User registerUser(RegisterRequest request) {
        
        User user = User.builder()
                .name(request.getName())
                .lastname(request.getSurname())
                .password(request.getPassword())
                .email(request.getEmail())
                .walletAddress(request.getWallet_address())
                .cvu(request.getCvu())
                .address(request.getAddress())
                .build();

        if (userRepository.findByEmail(request.getEmail()).isPresent()) throw new DuplicateEmail();

        return userRepository.save(user);
        
    }

    public User loginUser(LoginRequest request) {
        Optional<User> user_result = userRepository.findByEmail(request.getEmail());
        if (user_result.isEmpty() || !user_result.get().checkPassword(request.getPassword())) throw new InvalidCredentials();
        return user_result.get();
    }
}
