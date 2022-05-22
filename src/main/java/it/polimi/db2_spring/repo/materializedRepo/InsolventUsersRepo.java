package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.InsolventUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsolventUsersRepo extends JpaRepository<InsolventUsers, String> {
}
