package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
   List<Orders> findAllByUser(Users user);

   // to be checked
   @Query("select o from Orders o where o.user = ?1 and o.isRejected = true")
   List<Orders> findRejectedOrdersOfUser(Users user);
}
