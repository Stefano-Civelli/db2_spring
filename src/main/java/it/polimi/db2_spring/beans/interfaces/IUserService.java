package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CredentialsException;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface IUserService {

   Users create(Users user);
   Users get(String username);
   Users update(Users user);
   Boolean delete(String username);
   List<Users> getUserList(int limit);
   Boolean checkCredentials(Users user);

}
