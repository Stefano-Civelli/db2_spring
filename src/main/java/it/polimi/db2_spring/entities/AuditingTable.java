package it.polimi.db2_spring.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//When the same user causes three failed payments, an alert is created in a
// dedicated auditing table  SI FA CON TRIGGHER??
public class AuditingTable {
   @Id
   private Long id;
//   user Id,
//   username,
//   email
//        amount,
//   date and time of the last rejection
}