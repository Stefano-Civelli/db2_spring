package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IUserService;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

   private final UserRepo usrRepo;

   @Override
   public Users create(Users user) {
      log.info("saving new user " + user.getUsername() + " in the DB");
      return usrRepo.save(user);
   }

   @Override
   public Users get(String username) {
      log.info("getting from Db user: " + username);
      return usrRepo.getById(username);
   }

   @Override
   public Users update(Users user) {
      log.info("updating user " + user.getUsername() + " in the DB");
      return usrRepo.save(user);
   }

   @Override
   public Boolean delete(String username) {
      log.info("deleting user: " + username);
      usrRepo.deleteById(username);
      return TRUE;
   }

   @Override
   public List<Users> getUserList(int limit) {
      log.info("fetching user list");
      if(limit == 0)
         return usrRepo.findAll();
      return usrRepo.findAll().stream().limit(limit).collect(Collectors.toList());
   }


   @Override
   public Boolean checkCredentials(Users user) {

      Optional<Users> dbUser = usrRepo.findById(user.getUsername());
      if(dbUser.isEmpty())
         return Boolean.FALSE;

      return dbUser.get().authenticate(user.getPassword());


//      try {
//         uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn)
//                 .setParameter(2, pwd).getResultList();
//      } catch (PersistenceException e) {
//         throw new CredentialsException("Could not verify credentals");
//      }
//      if (uList.isEmpty())
//         return null;
//      else if (uList.size() == 1) {
//         User foundUser = uList.get(0);
//         return foundUser.getUsername();
//      }
//      throw new NonUniqueResultException("More than one user registered with same credentials");

   }


}
