package ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.errors;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.TransactionStatus;

public class InvalidTransactionOperation extends Error {

        public InvalidTransactionOperation(TransactionStatus status) {
            String message;
            message = switch (status) {
                case TRANSFER_SUCCESS:
                    yield "Transaction cannot be successfully updated as it is not in the pending state";
                case SUCCESS:
                    yield "Transaction cannot be successfully updated as it is not in the transfer success state";
                case CANCELED:
                    yield "Transaction cannot be canceled as it is already in the success state";
                case PENDING:
                    yield "Transaction cannot be pending as it is already in the pending state";
                default:
                    yield "Transaction cannot be updated the status is not valid";
            };
            super.setMessage(message);
            super.setCode("INVALID_TRANSACTION_OPERATION");
            super.setDescription("The transaction status is not valid");
        }

    }

