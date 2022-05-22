package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.RevenuePerOptionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RevenuePerOptionalProductRepo extends JpaRepository<RevenuePerOptionalProduct, Long> {
    @Query("select p from RevenuePerOptionalProduct p where p.isBest = true")
    RevenuePerOptionalProduct findBestOptionalProduct();
}
