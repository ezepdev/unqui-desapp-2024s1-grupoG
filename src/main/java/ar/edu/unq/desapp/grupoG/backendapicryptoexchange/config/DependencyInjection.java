package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DependencyInjection {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
