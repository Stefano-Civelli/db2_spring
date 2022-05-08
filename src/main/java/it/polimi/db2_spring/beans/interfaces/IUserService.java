package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CredentialsException;

import java.util.List;

public interface IUserService {

   Users create(Users user) throws CredentialsException;
   Users getByUsername(String username) throws CredentialsException;
   Users update(Users user);
   Boolean delete(String username);
   List<Users> getUserList(int limit);
   Boolean checkCredentials(Users user);
   Boolean incrementFailedPaymentsAndCheckForAlert(Users user);

}
