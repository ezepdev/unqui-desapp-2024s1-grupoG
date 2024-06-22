package ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final RestTemplate restTemplate;

    @Override
    public Long convertToArs(Double price_in_dollars) {
        HttpEntity<String> entity = getStringHttpEntity();

        // Enviar la solicitud GET
        var response = restTemplate.exchange("https://api.estadisticasbcra.com/usd", HttpMethod.GET, entity, List.class);
        @SuppressWarnings("unchecked")
        Map<String,Integer> last_dollar_record = (Map<String,Integer>) Objects.requireNonNull(response.getBody()).get(response.getBody().size() - 1);
        return (last_dollar_record.get("v") * price_in_dollars.longValue());
    }

    private static HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        var token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDc3OTcxNDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJlZGpwaW5oZWlyb0BnbWFpbC5jb20ifQ.Eqw-301_zMdHpKyeKTPJzeMlgiPkcuEtyFR7ATFB0n_KsUmc8kC5l6opFLCGflsi96jo23pNh4pnuIUyPKFR4w";
        headers.set("Authorization", "Bearer " + token);  // Añadir el token al encabezado de autorización

        // Crear la entidad HTTP con los encabezados
        return new HttpEntity<>(headers);
    }
}
