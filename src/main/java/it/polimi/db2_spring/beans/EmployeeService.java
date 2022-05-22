package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IEmployeeService;
import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.materializedViews.*;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.repo.AlertRepo;
import it.polimi.db2_spring.repo.EmployeeRepo;
import it.polimi.db2_spring.repo.materializedRepo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService {
   private final PurchasesPerPackageRepo purchasesPerPackageRepo;
   private final PurchasesPerPackageAndPeriodRepo purchasesPerPackageAndPeriodRepo;
   private final RevenuePerPackageRepo revenuePerPackageRepo;
   private final InsolventUsersRepo insolventUsersRepo;
   private final AlertRepo alertRepo;
   private final SuspendedOrdersRepo suspendedOrdersRepo;
   private final RevenuePerOptionalProductRepo revenuePerOptionalProductRepo;
   private final AverageOptionalPerPackageRepo averageOptionalPerPackageRepo;
   private final EmployeeRepo employeeRepo;

   @Override
   public Employee create (Employee employee) throws CredentialsException {
      log.info("saving new employee " + employee.getUsername() + " in the DB");

      Optional<Employee> employeeDb = employeeRepo.findById(employee.getUsername());

      if(!employeeDb.isEmpty() || !employeeRepo.findByMail(employee.getMail()).isEmpty() || !employee.adminPswOk())
         throw new CredentialsException("Username/Mail already present in the DB");
      return employeeRepo.save(employee);
   }

   @Override
   public Boolean checkCredentials(Employee employee) {
      Optional<Employee> dbEmployee = employeeRepo.findById(employee.getUsername());
      if(dbEmployee.isEmpty()) {
         log.info("authentication failed");
         return Boolean.FALSE;
      }
      Boolean authStatus = dbEmployee.get().authenticate(employee.getPassword(), employee.getInsertedAdminPsw());
      if(authStatus == TRUE)
         log.info("authentication succeeded");
      else
         log.info("authentication failed");
      return authStatus;
   }

   @Override
   public List<PurchasesPerPackage> fetchPurchasesPerPackage() {
      return purchasesPerPackageRepo.findAll();
   }

   @Override
   public List<PurchasesPerPackageAndPeriod> fetchPurchasesPerPackageAndValidityPeriod() {
      return purchasesPerPackageAndPeriodRepo.findAll();
   }

   @Override
   public List<RevenuePerPackage> fetchRevenuePerPackage() {
      return revenuePerPackageRepo.findAll();
   }

   @Override
   public List<InsolventUsers> fetchInsolventUsers() {
      return insolventUsersRepo.findAll();
   }

   @Override
   public List<Alert> fetchAlerts() {
      return alertRepo.findAll();
   }

   @Override
   public List<SuspendedOrders> fetchSuspendedOrders() {
      return suspendedOrdersRepo.findAll();
   }

   @Override
   public RevenuePerOptionalProduct fetchBestOptionalProduct() {
      return revenuePerOptionalProductRepo.findBestOptionalProduct();
   }

   @Override
   public List<AverageOptionalPerPackage> fetchAverageOptionalPerPackage() {
      return averageOptionalPerPackageRepo.findAll();
   }
}
