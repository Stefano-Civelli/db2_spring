package it.polimi.db2_spring.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

   @Transient
   private final String adminPassword = "admin";

   @Id
   private String username;
   @Column(nullable = false)
   private String password;
   @Basic(fetch = FetchType.LAZY)
   @Column(unique = true, nullable = false)
   private String mail;

   @Transient
   private String insertedAdminPsw;

   public Boolean adminPswOk () { return adminPassword.equals(insertedAdminPsw); }
   public Boolean authenticate(String pwd, String adminPsw) {
      return (pwd.equals(password) && adminPsw.equals(adminPassword));
   }
}
