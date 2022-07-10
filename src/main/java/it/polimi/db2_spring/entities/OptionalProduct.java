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
   @Column(nullable = false)
   private double monthlyFee;

   @JsonIgnore
   @ManyToMany(mappedBy = "products" ,
           cascade = {
           CascadeType.MERGE,
           CascadeType.REFRESH,
           CascadeType.DETACH},
           fetch = FetchType.LAZY)
   private List<ServicePKG> servicePKGList;

   @JsonIgnore
   @ManyToMany(mappedBy = "optionalProducts",
           cascade = {
           CascadeType.MERGE,
           CascadeType.REFRESH,
           CascadeType.DETACH},
           fetch = FetchType.LAZY)
   private  List<Orders> orders;
}