package it.polimi.db2_spring.repo.materializedRepo;


import it.polimi.db2_spring.entities.materializedViews.RevenuePerPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenuePerPackageRepo extends JpaRepository<RevenuePerPackage, Long> {
}
