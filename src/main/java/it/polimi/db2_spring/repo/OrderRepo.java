package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.utility.supportForQueries.IActivationSchedule;
import it.polimi.db2_spring.utility.supportForQueries.IOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
   List<Orders> findAllByUserOrderByCreationTimeDesc(Users user);

   @Query("select o from Orders o where o.user = ?1 and o.isRejected = true")
   List<IOrders> findRejectedOrdersOfUser(Users user);

   @Query("select o from Orders o where o.user = ?1 and o.isRejected = false and o.startingDateOfSubscription > current_date order by o.startingDateOfSubscription asc")
   List<IActivationSchedule> findActivationOfUser(Users user);
}
