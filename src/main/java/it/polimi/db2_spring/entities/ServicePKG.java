package it.polimi.db2_spring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePKG {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;

   @ManyToMany
   @JoinTable(name = "service_servicePkg")
   private List<Service> services;

   @OneToMany(mappedBy = "servicePKG")
   //@JsonManagedReference
   @JsonIgnore
   private List<Orders> ordersOfServ;

}
