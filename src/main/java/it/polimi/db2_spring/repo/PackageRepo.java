package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.ServicePKG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepo extends JpaRepository<ServicePKG, Long> {
    List<ServicePKG> findByName(String name);
}
