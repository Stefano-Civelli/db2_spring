package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.AverageOptionalPerPackage;
import it.polimi.db2_spring.utility.supportForQueries.IAverageOptionalPerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AverageOptionalPerPackageRepo extends JpaRepository<AverageOptionalPerPackage, Long> {
    @Query("select a from AverageOptionalPerPackage a")
    List<IAverageOptionalPerPackage> retrieveAverageOptionalPerPackage();
}
