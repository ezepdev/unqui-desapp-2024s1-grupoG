package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.Utils.mappers.CryptoMapper;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.api.contracts.Crypto.CryptoResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.CryptoCurrencySymbol;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.service.ICryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            description = "Get all cryptos with current price")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CryptoResponse.class), mediaType = "application/json") }) ,
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<CryptoResponse>> getAllCryptos() {

        List<CryptoCurrency> cryptos = cryptoService.allCurrencies();

        return ResponseEntity.ok(new CryptoMapper().mapToCryptoResponses(cryptos));
    }

    @Operation(
            summary = "Retrieve last 24 hours cotization",
            description = "Retrieve last 24 hours cotization for a specific crypto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CryptoResponse.class), mediaType = "application/json") }) ,
            @ApiResponse(responseCode = "404", description = "Crypto symbol not exist, please check this", content = { @Content(schema = @Schema())} )})
    @GetMapping("/{symbol}")
    public ResponseEntity<List<CryptoResponse>> getCotizationLastTwentyFourHours(@PathVariable String symbol) {

        List<CryptoCurrency> cryptos = cryptoService.getCotizationLastTwentyFourHours(CryptoCurrencySymbol.valueOf(symbol));
        return ResponseEntity.ok(new CryptoMapper().mapToCryptoResponses(cryptos));
    }
}
