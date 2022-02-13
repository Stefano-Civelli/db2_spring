package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {
   List<Orders> findAllByUser(Users user);
}
