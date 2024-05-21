package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.errors.InvalidTransactionOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_owner_id",nullable = false)
   private User userOwner;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_client_id",nullable = false)
   private User userClient;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "transaction_intention_id",nullable = false)
   private TransactionIntention intention;

   @Builder.Default
   private LocalDateTime created_at = LocalDateTime.now();

   @Builder.Default
   private TransactionStatus state = TransactionStatus.PENDING;

   public void confirmTransfer() {
      if (state != TransactionStatus.PENDING) throw InvalidTransactionOperation.builder().message("Transaction cannot be confirmed as it is not in the pending").build();
      state = TransactionStatus.TRANSFER_SUCCESS;
   }

   public void confirmReceipt() {
      if (state != TransactionStatus.TRANSFER_SUCCESS) throw InvalidTransactionOperation.builder().message("Transaction cannot be confirmed as it is not in the transfer success").build();
      // handle confirm receipt
      state = TransactionStatus.SUCCESS;


   }

   public void cancel() {
      if (state == TransactionStatus.SUCCESS) throw InvalidTransactionOperation.builder().message("Transaction cannot be canceled as it is already in the success state").build();
      state = TransactionStatus.CANCELED;
   }
}

