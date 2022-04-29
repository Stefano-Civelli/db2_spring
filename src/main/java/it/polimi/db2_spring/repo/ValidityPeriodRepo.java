package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.ValidityPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidityPeriodRepo extends JpaRepository<ValidityPeriod, Long> {
}