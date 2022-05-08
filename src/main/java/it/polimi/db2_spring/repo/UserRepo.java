package it.polimi.db2_spring.repo;

import it.polimi.db2_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users, String> {
    List<Users> findByMail(String mail);

}