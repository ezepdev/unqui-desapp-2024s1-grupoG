package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatusCode code;
    private String Description;
}
