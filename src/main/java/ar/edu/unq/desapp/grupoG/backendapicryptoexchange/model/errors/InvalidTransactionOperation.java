package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors;

import lombok.Builder;

public class InvalidTransactionOperation extends Error {

        @Builder
        InvalidTransactionOperation(String message, String description, String code) {
            super(message,description,code);
        }

        public final String getMessage(){
            return "Invalid operation type for current state of transaction";
        }

        public final String getCode(){
            return "INVALID_TRANSACTION_OPERATION";
        }

    }

