package it.polimi.db2_spring.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name = "serv_pkg", schema = "db2")
public class ServicePKG {
//
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
//   private String name;
//
//   @ManyToMany
//   //@JoinTable(name = "serv_pkg")
//   private List<Service> services;
//
//   @OneToMany(mappedBy = "servicePKG")
//   private List<Order> orders;
//
//
//   public ServicePKG() {
//   }
//
//   public List<Order> getOrders() {
//      return this.orders;
//   }
//
//   public void setOrders(List<Order> orders) {
//      this.orders = orders;
//   }
//
//   public Order addOrder(Order order) {
//      getOrders().add(order);
//      //order.setServicePKG(this);
//      return order;
//   }
//
//   public Order removeOrder(Order order) {
//      getOrders().remove(order);
//      //order.setServicePKG(null);
//      return order;
//   }
}
