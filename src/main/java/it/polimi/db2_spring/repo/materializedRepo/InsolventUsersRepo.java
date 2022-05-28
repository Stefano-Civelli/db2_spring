package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.InsolventUsers;
import it.polimi.db2_spring.utility.supportForQueries.IUsersInsolvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsolventUsersRepo extends JpaRepository<InsolventUsers, String> {
    @Query("select u from InsolventUsers u")
    List<IUsersInsolvent> retrieveInsolventUsers();
}
