package it.polimi.db2_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//A service activation schedule is a record of the services and optional products to
// activate for the user with their date of activation and date of deactivation
public class ActivationSchedule {
   @Id
   private Long id;
   @Temporal(TemporalType.DATE)
   private Date startingDate;
   @Temporal(TemporalType.DATE)
   private Date endingDate;

}
