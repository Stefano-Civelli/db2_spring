package it.polimi.db2_spring.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {
   @Id
   private String username;
   @NotEmpty
   private String password;
   @Basic(fetch = FetchType.LAZY)
   @Column(unique = true)
   @NotEmpty(message = "a mail is needed")
   private String mail;
   private Boolean isInsolvent;

   //add "imported" keys
   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //must be the same as the object on which I perform the JoinColumn
   @JsonManagedReference(value = "boia")
   private List<Orders> orders;

   public Boolean authenticate(String pwd) {
      return Objects.equals(pwd, this.password);
   }
}