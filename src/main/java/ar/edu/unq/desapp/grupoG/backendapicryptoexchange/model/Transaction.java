package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

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
   @JoinColumn(name = "transaction_offer_id",nullable = false)
   private TransactionIntention offer;
   @Builder.Default
   private TransactionState state = TransactionState.PENDING;
}

