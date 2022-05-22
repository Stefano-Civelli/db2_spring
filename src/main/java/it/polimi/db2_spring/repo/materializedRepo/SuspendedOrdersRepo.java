package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.SuspendedOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuspendedOrdersRepo extends JpaRepository<SuspendedOrders, Long> {
}
