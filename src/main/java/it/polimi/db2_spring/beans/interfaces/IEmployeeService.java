package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.exceptions.CredentialsException;

public interface IEmployeeService {
   Employee create (Employee employee) throws CredentialsException;
   Boolean checkCredentials(Employee employee);
}
