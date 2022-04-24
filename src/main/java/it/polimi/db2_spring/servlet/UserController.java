package it.polimi.db2_spring.servlet;


import it.polimi.db2_spring.beans.UserService;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   @PostMapping("/sign_up")
   public ResponseEntity<Response> signUpUser(@RequestBody @Valid Users user) {
      try {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("user", userService.create(user), "username", user.getUsername()))
                         .message("user created")
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


   @PostMapping("/log_in")
   public ResponseEntity<Response> logInUser(@RequestBody Users user) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("auth_status", userService.checkCredentials(user), "username", user.getUsername()))
                      .message("credentials checked")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   //for testing
   @GetMapping("/list")
   public ResponseEntity<Response> getPackages() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("users:", userService.getUserList(0)))
                      .message("users retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

}
