package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.Impl;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.LoginRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.authentication.RegisterRequest;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.DuplicateEmail;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors.InvalidCredentials;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IAuthService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.JWTService;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.common.AuthenticationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthenticationResult registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) throw new DuplicateEmail();

        User user = User.builder()
                .name(request.getName())
                .lastname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .walletAddress(request.getWallet_address())
                .cvu(request.getCvu())
                .address(request.getAddress())
                .build();

        String token = jwtService.generateToken(user);
        userRepository.save(user);
        return new AuthenticationResult(token,user.getName() + " " + user.getLastname(),user.getEmail());
        
    }

    @Override
    public AuthenticationResult loginUser(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Optional<User> userResult = userRepository.findByEmail(request.getEmail());
        if (userResult.isEmpty()) throw new InvalidCredentials();
        String token = jwtService.generateToken(userResult.get());

        return new AuthenticationResult(token,userResult.get().getName() + " " + userResult.get().getLastname(),userResult.get().getEmail());
    }
}
