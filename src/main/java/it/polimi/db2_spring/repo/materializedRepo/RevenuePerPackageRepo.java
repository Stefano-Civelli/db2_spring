package it.polimi.db2_spring.repo.materializedRepo;


import it.polimi.db2_spring.entities.materializedViews.RevenuePerPackage;
import it.polimi.db2_spring.utility.supportForQueries.IRevenuePerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevenuePerPackageRepo extends JpaRepository<RevenuePerPackage, Long> {
    @Query("select r from RevenuePerPackage r")
    List<IRevenuePerPackage> retrieveRevenuePerPackage();
}
