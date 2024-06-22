package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    BinanceService binanceService;
    @Autowired
    ICryptoRepository cryptoRepository;

    @Override
    public void run(String... args) throws Exception {
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
