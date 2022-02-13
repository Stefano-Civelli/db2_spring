package it.polimi.db2_spring.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionalProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long Id;
   private double monthlyFee;
}
