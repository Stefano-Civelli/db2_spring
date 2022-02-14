package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.ActivationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationScheduleRepo extends JpaRepository<ActivationSchedule, Long> {
}
