package it.polimi.db2_spring.servlet;


import it.polimi.db2_spring.beans.EmployeeService;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

   private final EmployeeService employeeService;

   @PostMapping("/sign_up")
   public ResponseEntity<Response> signUpUser(@RequestBody @Valid Employee employee) {
      try {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("user", employeeService.create(employee), "username", employee.getUsername()))
                         .message("employee created")
                         .status(CREATED)
                         .statusCode(CREATED.value())
                         .build()
         );
      } catch (CredentialsException e) {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .message(e.getMessage())
                         .status(CONFLICT)
                         .statusCode(CONFLICT.value())
                         .build()
         );
      }
   }

   //aggiungere endpoint per login dell' Employee

}
