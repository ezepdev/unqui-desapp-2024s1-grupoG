package ar.edu.unq.desapp.grupog.backendapicryptoexchange;


import ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories.ICryptoRepository;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IBinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdaterCryptoPrices {

    private final ICryptoRepository cryptoRepository;
    private final IBinanceService binanceService;

    @Scheduled(fixedRateString = "${crypto.update.interval}")
    public void updateCryptoPrices() {
        cryptoRepository.saveAll(binanceService.getUpdatedCryptoPrices());
    }
}
