package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, String> {


}
