package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.ServicePKG;

import java.util.List;

public interface IPackageService {

   List<ServicePKG> getPackageList(int limit);
   Boolean delete(Long Id);
}
