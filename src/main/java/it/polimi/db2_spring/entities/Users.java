package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
   @Id
   private String username;

   @Column(nullable = false)
   private String password;

   @Basic(fetch = FetchType.LAZY)
   @Column(unique = true, nullable = false)
   private String mail;
   private Boolean isInsolvent;
   @Column(columnDefinition = "integer default 0")
   private int failedPayments;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JsonManagedReference(value = "reference")
   private List<Orders> orders;


   @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Alert> alerts;

   public Boolean authenticate(String pwd) {
      return Objects.equals(pwd, this.password);
   }

   public void incrementFailedPayments() {
      failedPayments++;
   }
}