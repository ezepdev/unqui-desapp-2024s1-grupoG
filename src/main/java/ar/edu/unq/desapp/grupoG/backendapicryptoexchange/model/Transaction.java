package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@Builder
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

}

