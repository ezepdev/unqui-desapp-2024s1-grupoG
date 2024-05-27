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
   private Long id;

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

   @Enumerated(EnumType.STRING)
   @Column(name = "transaction_status",nullable = false)
   @Builder.Default
   private TransactionStatus status = TransactionStatus.PENDING;

   public void confirmTransfer() {
      if (status != TransactionStatus.PENDING) throw new InvalidTransactionOperation(TransactionStatus.TRANSFER_SUCCESS);
      status = TransactionStatus.TRANSFER_SUCCESS;
   }

   public void confirmReceipt() {
      if (status != TransactionStatus.TRANSFER_SUCCESS) throw new InvalidTransactionOperation(TransactionStatus.SUCCESS);
      // handle confirm receipt
      status = TransactionStatus.SUCCESS;
   }

   public void cancel() {
      if (status == TransactionStatus.SUCCESS) throw new InvalidTransactionOperation(TransactionStatus.CANCELED);
      status = TransactionStatus.CANCELED;
   }

   public boolean IsUserImplicated(User user) {
      return userOwner.getId().equals(user.getId()) || userClient.getId().equals(user.getId());
   }
}

