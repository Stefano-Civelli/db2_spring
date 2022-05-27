package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.PurchasesPerPackage;
import it.polimi.db2_spring.utility.supportForQueries.IPurchasesWithName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PurchasesPerPackageRepo extends JpaRepository<PurchasesPerPackage, Long> {
    @Query("select p from PurchasesPerPackage p")
    List<IPurchasesWithName> retrievePurchasesWithName();
}
