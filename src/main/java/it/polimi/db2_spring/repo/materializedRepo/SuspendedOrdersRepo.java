package it.polimi.db2_spring.repo.materializedRepo;

import it.polimi.db2_spring.entities.materializedViews.SuspendedOrders;
import it.polimi.db2_spring.utility.supportForQueries.ISuspendedOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuspendedOrdersRepo extends JpaRepository<SuspendedOrders, Long> {
    @Query("select s from SuspendedOrders s")
    List<ISuspendedOrders> retrieveSuspendedOrders();
}
