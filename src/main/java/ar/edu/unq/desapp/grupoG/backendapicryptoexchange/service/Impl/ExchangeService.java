package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.IExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService implements IExchangeService {

    @Autowired
    private final RestTemplate restTemplate;

    @Override
    public Long convertToArs(Double price_in_dollars) {
        HttpHeaders headers = new HttpHeaders();
        var token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NDc3OTcxNDYsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJlZGpwaW5oZWlyb0BnbWFpbC5jb20ifQ.Eqw-301_zMdHpKyeKTPJzeMlgiPkcuEtyFR7ATFB0n_KsUmc8kC5l6opFLCGflsi96jo23pNh4pnuIUyPKFR4w";
        headers.set("Authorization", "Bearer " + token);  // Añadir el token al encabezado de autorización

        // Crear la entidad HTTP con los encabezados
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Enviar la solicitud GET
        var response = restTemplate.exchange("https://api.estadisticasbcra.com/usd", HttpMethod.GET, entity, List.class);
        LinkedHashMap<String,Integer> last_dollar_record = (LinkedHashMap<String, Integer>) response.getBody().getLast();
        return (last_dollar_record.get("v") * price_in_dollars.longValue());
    }
}
