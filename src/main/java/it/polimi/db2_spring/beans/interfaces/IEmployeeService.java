package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.materializedViews.*;
import it.polimi.db2_spring.exceptions.CredentialsException;

import java.util.List;

public interface IEmployeeService {
   Employee create (Employee employee) throws CredentialsException;
   Boolean checkCredentials(Employee employee);
   List<PurchasesPerPackage> fetchPurchasesPerPackage();
   List<PurchasesPerPackageAndPeriod> fetchPurchasesPerPackageAndValidityPeriod();
   List<RevenuePerPackage> fetchRevenuePerPackage();
   List<InsolventUsers> fetchInsolventUsers();
   List<Alert> fetchAlerts();
   List<SuspendedOrders> fetchSuspendedOrders();
   RevenuePerOptionalProduct fetchBestOptionalProduct();
   List<AverageOptionalPerPackage> fetchAverageOptionalPerPackage();
}
