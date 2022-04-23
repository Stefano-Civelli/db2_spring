package it.polimi.db2_spring.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionalProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long productCode;
   private String briefProductDescription;
   // magari aggiungere un product type
   private double monthlyFee;

   @JsonIgnore
   @ManyToMany(mappedBy = "products")
   private List<ServicePKG> servicePKGList;

   @JsonIgnore
   @ManyToMany(mappedBy = "optionalProducts")
   private  List<Orders> orders;
}