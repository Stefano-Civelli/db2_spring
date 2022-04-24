package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IEmployeeService;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService {

   private final EmployeeRepo employeeRepo;

   @Override
   public Employee create (Employee employee) throws CredentialsException {
      log.info("saving new employee " + employee.getUsername() + " in the DB");

      Optional<Employee> employeeDb = employeeRepo.findById(employee.getUsername());

      if(!employeeDb.isEmpty() || !employeeRepo.findByMail(employee.getMail()).isEmpty() || !employee.adminPswOk())
         throw new CredentialsException("Username/Mail already present in the DB");
      return employeeRepo.save(employee);
   }
}
