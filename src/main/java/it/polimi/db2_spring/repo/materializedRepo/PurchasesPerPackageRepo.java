package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.PurchasesPerPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesPerPackageRepo extends JpaRepository<PurchasesPerPackage, Long> {
}
