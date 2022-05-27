package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IEmployeeService;
import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.materializedViews.*;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.repo.AlertRepo;
import it.polimi.db2_spring.repo.EmployeeRepo;
import it.polimi.db2_spring.repo.PackageRepo;
import it.polimi.db2_spring.repo.materializedRepo.*;
import it.polimi.db2_spring.utility.supportForQueries.*;
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
   public List<IPurchasesWithName> fetchPurchasesPerPackage() {
      return purchasesPerPackageRepo.retrievePurchasesWithName();
   }

   @Override
   public List<IPurchasesWithNameAndValidity> fetchPurchasesPerPackageAndValidityPeriod() {
      return purchasesPerPackageAndPeriodRepo.retrievePurchasesWithNameAndValidity();
   }

   @Override
   public List<IRevenuePerPackage> fetchRevenuePerPackage() {
      return revenuePerPackageRepo.retrieveRevenuePerPackage();
   }

   @Override
   public List<IUsersInsolvent> fetchInsolventUsers() {
      return insolventUsersRepo.retrieveInsolventUsers();
   }

   @Override
   public List<IAlert> fetchAlerts() {
      return alertRepo.retrieveAlerts();
   }

   @Override
   public List<ISuspendedOrders> fetchSuspendedOrders() {
      return suspendedOrdersRepo.retrieveSuspendedOrders();
   }

   @Override
   public List<RevenuePerOptionalProduct> fetchBestOptionalProduct() {
      return revenuePerOptionalProductRepo.findBestOptionalProduct();
   }

   @Override
   public List<IAverageOptionalPerPackage> fetchAverageOptionalPerPackage() {
      return averageOptionalPerPackageRepo.retrieveAverageOptionalPerPackage();
   }
}
