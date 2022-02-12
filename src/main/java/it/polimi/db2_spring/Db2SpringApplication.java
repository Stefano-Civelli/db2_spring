package it.polimi.db2_spring;

import it.polimi.db2_spring.entities.User;
import it.polimi.db2_spring.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.lang.Boolean.FALSE;

@SpringBootApplication
public class Db2SpringApplication {

   public static void main(String[] args) {
      SpringApplication.run(Db2SpringApplication.class, args);
   }

//   @Bean
//   CommandLineRunner run(UserRepo userRepo) {
//      return args -> {
//         System.out.println("ciao");
//         userRepo.save(new User("talla", "pescegrosso", "talla.rita@hotmail.it", FALSE, FALSE));
//         userRepo.save(new User("talla1", "pescegrosso1", "talla1.rita@hotmail.it", FALSE, FALSE));
//         userRepo.save(new User("talla2", "pescegrosso2", "talla2.rita@hotmail.it", FALSE, FALSE));
//         userRepo.save(new User("talla3", "pescegrosso3", "talla3.rita@hotmail.it", FALSE, FALSE));
//         System.out.println("ciao");
//      };
//   }

}
