package it.polimi.db2_spring.repo.materializedRepo;


import it.polimi.db2_spring.entities.materializedViews.PurchasesPerPackageAndPeriod;
import it.polimi.db2_spring.utility.PurchasesPerPackageAndPeriodComposedPrimaryKey;
import it.polimi.db2_spring.utility.supportForQueries.IPurchasesWithNameAndValidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchasesPerPackageAndPeriodRepo extends JpaRepository<PurchasesPerPackageAndPeriod, PurchasesPerPackageAndPeriodComposedPrimaryKey> {
    @Query("select p from PurchasesPerPackageAndPeriod p")
    List<IPurchasesWithNameAndValidity> retrievePurchasesWithNameAndValidity();
}