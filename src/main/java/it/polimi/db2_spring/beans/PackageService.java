package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IPackageService;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.ServicePKG;
import it.polimi.db2_spring.repo.PackageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PackageService implements IPackageService {

   private final PackageRepo packageRepo;

   @Override
   public List<ServicePKG> getPackageList(int limit) {
      log.info("fetching Service Package list");
      if(limit == 0)
         return packageRepo.findAll();
      return packageRepo.findAll().stream().limit(limit).collect(Collectors.toList());
   }

   @Override
   public ServicePKG create(ServicePKG servicePackage) {
      log.info("saving new service package " + servicePackage.getId() + " in the DB");
      return packageRepo.save(servicePackage);
   }

   @Override
   public Boolean delete(Long id) {
      log.info("deleting Package: " + id);
      packageRepo.deleteById(id);
      return TRUE;
   }

   @Override
   public ServicePKG getPackageById(Long id) throws EntityNotFoundException {
      log.info("fetching Package: " + id);
      Optional<ServicePKG> pkgDb = packageRepo.findById(id);
      if(pkgDb.isEmpty())
         throw new EntityNotFoundException();

      return pkgDb.get();
   }
}
