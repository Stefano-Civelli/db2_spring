package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

   @Column(nullable = false)
   private String name;

   @ManyToMany(fetch=FetchType.EAGER,
           cascade = {
           CascadeType.MERGE,
           CascadeType.REFRESH,
           CascadeType.DETACH})
   @Fetch(value = FetchMode.SUBSELECT)
   @JoinTable(name = "service_servicePkg")
   private List<Services> services;

   @OneToMany(mappedBy = "servicePKG", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JsonIgnore
   private List<Orders> ordersOfServ;

   @ManyToMany (fetch=FetchType.EAGER,
           cascade = {
           CascadeType.MERGE,
           CascadeType.REFRESH,
           CascadeType.DETACH})
   @Fetch(value = FetchMode.SUBSELECT)
   @JoinTable(name = "service_optProduct")
   private List<OptionalProduct> products;


   @ManyToMany (fetch=FetchType.EAGER,
           cascade = {
           CascadeType.MERGE,
           CascadeType.REFRESH,
           CascadeType.DETACH})
   @Fetch(value = FetchMode.SUBSELECT)
   @JoinTable(name = "servicePkg_period")
   private List<ValidityPeriod> periods;
}
