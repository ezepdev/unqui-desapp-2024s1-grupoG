package ar.edu.unq.desapp.grupog.backendapicryptoexchange.repositories;

import java.time.LocalDate;
import java.util.List;

public interface CustomTransactionRepository {
    List<TradedVolume> tradedVolumeCryptosBetweenDates(Long id, LocalDate fromDate, LocalDate toDate);
}
