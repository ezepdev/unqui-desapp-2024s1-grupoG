package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import java.util.Date;

public class TransactionIntention {
    TransactionIntentionType type;
    CryptoCurrency currency;
    Float currency_amount;
    Float price;
    User user;
    Date creationDate;
}
