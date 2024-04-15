package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CryptoCurrency {
    String name;
    Float price;
}
