package it.polimi.db2_spring.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//When the same user causes three failed payments, an alert is created in a
// dedicated auditing table  SI FA CON TRIGGHER??
public class AuditingTable /* ALERT */ {
   @Id
   private LocalDateTime timeStampOfLastRejection;
   @ManyToOne
   private Users user;
   private double amount;

//   user Id,
//   username,
//   email
//        amount,
//   date and time of the last rejection
}