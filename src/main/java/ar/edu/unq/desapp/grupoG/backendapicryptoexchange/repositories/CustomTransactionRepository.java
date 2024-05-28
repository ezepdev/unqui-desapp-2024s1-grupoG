package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.repositories;

import java.time.LocalDate;
import java.util.List;

public interface CustomTransactionRepository {
    List<TradedVolume> tradedVolumeCryptosBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
