package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {


}
