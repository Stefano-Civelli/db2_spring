package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.AverageOptionalPerPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageOptionalPerPackageRepo extends JpaRepository<AverageOptionalPerPackage, Long> {
}
