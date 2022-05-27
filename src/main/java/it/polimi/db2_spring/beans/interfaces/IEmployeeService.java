package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.materializedViews.*;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.utility.supportForQueries.*;

import java.util.List;

public interface IEmployeeService {
   Employee create (Employee employee) throws CredentialsException;
   Boolean checkCredentials(Employee employee);
   List<IPurchasesWithName> fetchPurchasesPerPackage();
   List<IPurchasesWithNameAndValidity> fetchPurchasesPerPackageAndValidityPeriod();
   List<IRevenuePerPackage> fetchRevenuePerPackage();
   List<IUsersInsolvent> fetchInsolventUsers();
   List<IAlert> fetchAlerts();
   List<ISuspendedOrders> fetchSuspendedOrders();
   RevenuePerOptionalProduct fetchBestOptionalProduct();
   List<AverageOptionalPerPackage> fetchAverageOptionalPerPackage();
}
