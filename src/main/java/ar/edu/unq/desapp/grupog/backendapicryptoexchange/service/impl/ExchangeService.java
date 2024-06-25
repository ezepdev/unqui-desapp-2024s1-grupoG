package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExchangeService implements IExchangeService {

    private final RestTemplate restTemplate;

    @Value("${api.dollar.token}")
    private String apiToken;
    @Override
    public Long convertToArs(Double priceInDollars) {
        HttpEntity<String> entity = getStringHttpEntity();
        Integer currentDollarPrice = 1000;
        // Enviar la solicitud GET
        var response = restTemplate.exchange("https://api.estadisticasbcra.com/usd", HttpMethod.GET, entity, List.class);
        if (response.hasBody()) {
            var dollarPrices = response.getBody();
            var lastDollarRecord = (Map<String, Integer>) Objects.requireNonNull(dollarPrices).get(dollarPrices.size() - 1);
            currentDollarPrice = lastDollarRecord.get("v");
        }
        return priceInDollars.longValue() * currentDollarPrice;

    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + apiToken);

        // Crear la entidad HTTP con los encabezados
        return new HttpEntity<>(headers);
    }
}
