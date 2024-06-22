package ar.edu.unq.desapp.grupoG.backendapicryptoexchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class BackendApiCryptoexchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiCryptoexchangeApplication.class, args);
	}

}
