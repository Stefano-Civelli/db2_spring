package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IUserService;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CredentialsException;
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
   public Users create(Users user) throws CredentialsException {
      log.info("saving new user " + user.getUsername() + " in the DB");

      Optional<Users> userDb = usrRepo.findById(user.getUsername());

      if(!userDb.isEmpty() || !usrRepo.findByMail(user.getMail()).isEmpty())
         throw new CredentialsException("Username/Mail already present in the DB");
      user.setIsInsolvent(false);
      return usrRepo.save(user);
   }

   @Override
   public Users getByUsername(String username) throws CredentialsException{
      Optional<Users> dbUser = usrRepo.findById(username);

      log.info("getting from Db user: " + username);

      if(dbUser.isEmpty())
         throw new CredentialsException("User you tried to retrieve is not present in the DB");

      return dbUser.get();
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
      if(dbUser.isEmpty()) {
         log.info("authentication failed");
         return Boolean.FALSE;
      }
      Boolean authStatus = dbUser.get().authenticate(user.getPassword());
      if(authStatus == TRUE)
         log.info("authentication succeeded");
      else
         log.info("authentication failed");
      return authStatus;
   }

   @Override
   public Boolean incrementFailedPaymentsAndCheckForAlert(Users user) {
      user.incrementFailedPayments();
      return user.getFailedPayments() % 3 == 0;
   }

}
