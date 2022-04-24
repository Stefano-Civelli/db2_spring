package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
   List<Employee> findByMail(String mail);
}
