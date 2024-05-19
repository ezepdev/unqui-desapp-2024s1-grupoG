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
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private User userOwner;
   private User userClient;
   private TransactionOffer offer;
   @Builder.Default
   private TransactionState state = TransactionState.PENDING;
}

