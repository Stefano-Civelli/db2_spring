package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.utility.supportForQueries.IAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertRepo extends JpaRepository<Alert, Long> {
    List<Alert> findByUser(Users user);

    @Query("select a from Alert a")
    List<IAlert> retrieveAlerts();
}
