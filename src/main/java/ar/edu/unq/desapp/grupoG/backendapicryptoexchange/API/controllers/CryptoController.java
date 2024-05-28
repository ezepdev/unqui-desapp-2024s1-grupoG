package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers.CryptoMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Crypto.CryptoResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.TransactionIntention.TransactionIntentionResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.Impl.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cryptos", description = "Cryptos APIs")

public class CryptoController {

    private final ICryptoService cryptoService;

    @Operation(
            summary = "Retrieve all cryptos",
            description = "Get all cryptos with current price",
            tags = { "cryptos", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CryptoResponse.class), mediaType = "application/json") }) ,
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<CryptoResponse>> getAllCryptos() {

        List<CryptoCurrency> cryptos = cryptoService.allCurrencies();

        return ResponseEntity.ok(new CryptoMapper().mapToCryptoResponses(cryptos));
    }
}
