package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.User;
import it.polimi.db2_spring.exceptions.CredentialsException;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface IUserService {

   User create(User user);
   User get(String username);
   User update(User user);
   Boolean delete(String username);
   List<User> getUserList(int limit);
   Boolean checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException;

}
