package it.polimi.db2_spring.repo.materializedRepo;


import it.polimi.db2_spring.entities.materializedViews.PurchasesPerPackageAndPeriod;
import it.polimi.db2_spring.utility.PurchasesPerPackageAndPeriodComposedPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesPerPackageAndPeriodRepo extends JpaRepository<PurchasesPerPackageAndPeriod, PurchasesPerPackageAndPeriodComposedPrimaryKey> {
}