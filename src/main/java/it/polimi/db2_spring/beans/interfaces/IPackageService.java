package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.ServicePKG;
import it.polimi.db2_spring.exceptions.CredentialsException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IPackageService {

   List<ServicePKG> getPackageList(int limit);
   ServicePKG create(ServicePKG servicePackage) throws CredentialsException;
   Boolean delete(Long Id);
   ServicePKG getPackageById(Long id) throws EntityNotFoundException;
}
