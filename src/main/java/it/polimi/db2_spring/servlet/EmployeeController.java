package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.EmployeeService;
import it.polimi.db2_spring.beans.OptionalService;
import it.polimi.db2_spring.beans.PackageService;
import it.polimi.db2_spring.entities.Employee;
import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.ServicePKG;
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
   private final OptionalService optionalService;
   private final PackageService packageService;

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

   @PostMapping("/log_in")
   public ResponseEntity<Response> logInUser(@RequestBody Employee employee) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("auth_status", employeeService.checkCredentials(employee), "username", employee.getUsername()))
                      .message("credentials checked")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/purchases_per_package")
   public ResponseEntity<Response> retrievePurchasesPerPackage() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("purchases", employeeService.fetchPurchasesPerPackage()))
                      .message("fetched purchases per package")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/purchases_per_package_and_period")
   public ResponseEntity<Response> retrievePurchasesPerPackageAndValidityPeriod() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("purchases", employeeService.fetchPurchasesPerPackageAndValidityPeriod()))
                      .message("fetched purchases per package and period")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/revenue_per_package")
   public ResponseEntity<Response> retrieveRevenuePerPackage() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("revenues", employeeService.fetchRevenuePerPackage()))
                      .message("fetched revenue per package")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/list_of_insolvent_users")
   public ResponseEntity<Response> retrieveInsolventUsers() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("users", employeeService.fetchInsolventUsers()))
                      .message("fetched insolvent users")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/list_of_alerts")
   public ResponseEntity<Response> retrieveAlerts() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("alerts", employeeService.fetchAlerts()))
                      .message("fetched alerts")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }


   @GetMapping("/list_of_suspended_orders")
   public ResponseEntity<Response> retrieveSuspendedOrders() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("orders", employeeService.fetchSuspendedOrders()))
                      .message("fetched suspended orders")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/best_optional_product")
   public ResponseEntity<Response> retrieveBestOptionalProduct() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("optional_product", employeeService.fetchBestOptionalProduct()))
                      .message("fetched best optional product")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @GetMapping("/average_optional_per_package")
   public ResponseEntity<Response> retrieveAverageNumberOfOptionalPerPackage() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("average_optionals_per_package", employeeService.fetchAverageOptionalPerPackage()))
                      .message("fetched average optional per package")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }
}
