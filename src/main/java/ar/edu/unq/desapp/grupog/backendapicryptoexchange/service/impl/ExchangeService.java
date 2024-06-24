package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Long convertToArs(Double priceInDollars) {
        HttpEntity<String> entity = getStringHttpEntity();
        Integer currentDollarPrice = 1000;
        // Enviar la solicitud GET
        var response = restTemplate.exchange("https://api.estadisticasbcra.com/usd", HttpMethod.GET, entity, List.class);
        if (response.hasBody()) {
            var dollarPrices = response.getBody();
            Map<String,Integer> lastDollarRecord = (Map<String, Integer>) Objects.requireNonNull(dollarPrices).get(dollarPrices.size() - 1);
            currentDollarPrice = lastDollarRecord.get("v");
        }
        return priceInDollars.longValue() * currentDollarPrice;

    }

    private static HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        var token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDc3OTcxNDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJlZGpwaW5oZWlyb0BnbWFpbC5jb20ifQ.Eqw-301_zMdHpKyeKTPJzeMlgiPkcuEtyFR7ATFB0n_KsUmc8kC5l6opFLCGflsi96jo23pNh4pnuIUyPKFR4w";
        headers.set("Authorization", "Bearer " + token);  // Añadir el token al encabezado de autorización

        // Crear la entidad HTTP con los encabezados
        return new HttpEntity<>(headers);
    }
}
