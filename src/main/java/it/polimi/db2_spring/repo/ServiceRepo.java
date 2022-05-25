package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Services, Long> {
}
