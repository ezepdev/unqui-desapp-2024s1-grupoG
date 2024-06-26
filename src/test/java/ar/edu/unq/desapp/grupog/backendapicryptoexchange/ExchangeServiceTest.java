package ar.edu.unq.desapp.grupog.backendapicryptoexchange;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.impl.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExchangeServiceTest {
    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testConvertToArs() {
        List<Map<String, Integer>> mockResponse = List.of(Map.of("v", 200));
        ResponseEntity<List> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(List.class)))
                .thenReturn(responseEntity);

        Long convertedPrice = exchangeService.convertToArs(50.0);


        assertEquals(10000, convertedPrice);
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(List.class));
    }

    @Test
    void testConvertToArs_noBody() {

        ResponseEntity<List> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);


        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(List.class)))
                .thenReturn(responseEntity);


        Long convertedPrice = exchangeService.convertToArs(50.0);


        assertEquals(50000, convertedPrice);
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(List.class));
    }
}
