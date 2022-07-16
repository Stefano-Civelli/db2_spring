package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.polimi.db2_spring.utility.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long Id;

   @Column(nullable = false)
   private ServiceType serviceType;

   @Column(columnDefinition = "integer default 0")
   private int numberOfMinutes;
   @Column(columnDefinition = "integer default 0")
   private int numberOfSMS;
   @Column(columnDefinition = "integer default 0")
   private int numberOfGigabytes;
   @Column(columnDefinition = "double default 0")
   private double feeExtraMin;
   @Column(columnDefinition = "double default 0")
   private double feeExtraSMS;
   @Column(columnDefinition = "integer default 0")
   private int feeExtraGigabytes;

   @JsonIgnore
   @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<ServicePKG> servicePKGS;
}
