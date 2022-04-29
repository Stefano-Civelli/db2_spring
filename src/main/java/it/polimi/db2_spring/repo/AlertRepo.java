package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepo extends JpaRepository<Alert, Long> {
    List<Alert> findByUser(Users user);
}
