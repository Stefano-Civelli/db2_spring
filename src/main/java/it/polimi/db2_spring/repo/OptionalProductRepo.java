package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.OptionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionalProductRepo extends JpaRepository<OptionalProduct, Long> {
}
