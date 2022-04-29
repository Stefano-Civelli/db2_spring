package it.polimi.db2_spring.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

   @Transient
   private final String adminPassword = "snitches get stitches and wind up in ditches";

   @Id
   private String username;
   @NotEmpty
   private String password;
   @Basic(fetch = FetchType.LAZY)
   @Column(unique = true)
   @NotEmpty(message = "a mail is needed")
   private String mail;

   @Transient
   private String insertedAdminPsw;

   public Boolean adminPswOk () { return adminPassword.equals(insertedAdminPsw); }
   public Boolean authenticate(String pwd, String adminPsw) {
      return (pwd.equals(password) && adminPsw.equals(adminPassword));
   }
}