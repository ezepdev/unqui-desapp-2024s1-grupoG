package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;


import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.LoginRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.RegisterRequest;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.EmailAlreadyInUseError;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.UserNotFound;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
// TODO: SEE TRANSACTIONABLE IMPLEMENTATION
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;

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

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseError();
        }

        return userRepository.save(user);
        
    }

    public User loginUser(LoginRequest request) {
        Optional<User> user_result = userRepository.findByEmail(request.getEmail());
        if (user_result.isEmpty()) throw new UserNotFound();
        return user_result.get();
    }
}
