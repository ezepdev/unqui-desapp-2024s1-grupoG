package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;

    @Override
    public void run(String... args) {
        var user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .lastname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("PASSWORD")
                .cvu("123456789123456789123")
                .build();
        var anotherUser = User.builder()
                .name("Juan")
                .email("JUAN.EMAIL@GMAIL.COM")
                .lastname("TORRES")
                .walletAddress("12345679")
                .address("ADDRESS")
                .password("PaSSwORD$123")
                .cvu("123456789123456789125")
                .build();
        userRepository.save(user);
        userRepository.save(anotherUser);

    }
}
