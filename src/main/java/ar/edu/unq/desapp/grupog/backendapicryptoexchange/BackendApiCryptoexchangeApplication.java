package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
@Generated
public class BackendApiCryptoexchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApiCryptoexchangeApplication.class, args);
    }
}
