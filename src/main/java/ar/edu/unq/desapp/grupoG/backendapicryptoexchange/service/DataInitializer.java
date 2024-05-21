package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    IUserRepository user_repository;
    @Autowired
    BinanceService binanceService;
    @Autowired
    ICryptoRepository ICryptoRepository;

    @Override
    public void run(String... args) throws Exception {
        var user = User.builder()
                .name("Jose")
                .email("JOSE.EMAIL@GMAIL.COM")
                .surname("AMALFITANI")
                .walletAddress("12345678")
                .address("ADDRESS")
                .password("PASSWORD")
                .cvu("123456789123456789123")
                .build();
        ICryptoRepository.saveAll(binanceService.getUpdatedCryptoPrices());
        user_repository.save(user);
    }
}
