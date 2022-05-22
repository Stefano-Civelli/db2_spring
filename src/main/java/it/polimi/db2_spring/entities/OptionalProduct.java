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
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String briefProductDescription;
   // magari aggiungere un product type
   @Column(nullable = false)
   private double monthlyFee;

   @JsonIgnore
   @ManyToMany(mappedBy = "products" /*, fetch = FetchType.EAGER*/)
   private List<ServicePKG> servicePKGList;

   @JsonIgnore
   @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.EAGER)
   private  List<Orders> orders;
}