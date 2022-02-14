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
public class Service {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long Id;
   private int numberOfMinutes;
   private int numberOfSMS;
   private int numberOfGigabytes;
   private double feeExtraMin;
   private double feeExtraSMS;
   private int feeExtraGigabytes;
   private ServiceType serviceType;

   @JsonIgnore
   @ManyToMany(mappedBy = "services")
   private List<ServicePKG> servicePKGS;
}
