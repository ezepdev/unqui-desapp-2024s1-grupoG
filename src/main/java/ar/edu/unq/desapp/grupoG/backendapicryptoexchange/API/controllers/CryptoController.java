package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cryptos")
@RequiredArgsConstructor
public class CryptoController {

    private final ICryptoService cryptoService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CryptoCurrency>> getAllCryptos() {
        return ResponseEntity.ok(cryptoService.allCurrencies());
    }
}
