package it.polimi.db2_spring.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u  WHERE u.username = ?1 and u.password = ?2")
public class Users {
   @Id
   private String username;
   @NotEmpty
   private String password;
   @Basic(fetch = FetchType.LAZY)
   @Column(unique = true)
   @NotEmpty(message = "a mail is needed")
   private String mail;
   private Boolean insolvent;
   private Boolean isAdmin;


   public Boolean authenticate(String pwd) {
      return Objects.equals(pwd, this.password);
   }

   //--------------------------------------------------------------------------
//
//
//   //add "imported" keys
//   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //must be the same as the object on which I perform the JoinColumn
//   private List<Order> orders;
//
//
//
//
//   public User(String username, String password, String mail) {
//      this.username = username;
//      this.password = password;
//      this.mail = mail;
//      this.insolvent = false;
//   }
//
//
//   public List<Order> getOrders() {
//      return this.orders;
//   }
//
//   public void setOrders(List<Order> orders) {
//      this.orders = orders;
//   }
//
////   public Order addOrder(Order order) {
////      getOrders().add(order);
////      order.setUser(this);
////      return order;
////   }
////
////   public Order removeOrder(Order order) {
////      getOrders().remove(order);
////      order.setUser(null);
////      return order;
////   }
}