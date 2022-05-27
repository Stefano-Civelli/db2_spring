package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.PackageService;
import it.polimi.db2_spring.entities.ServicePKG;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/serv_pkg")
@RequiredArgsConstructor
public class PackageController {

   private final PackageService packageService;

   @GetMapping("/list")
   public ResponseEntity<Response> getPackages() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("packages", packageService.getPackageList(0)))
                      .message("packages retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }


   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Response> deletePackage(@PathVariable("id") Long id) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("deleted", packageService.delete(id)))
                      .message("package deleted")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @PostMapping("/create_package")
   public ResponseEntity<Response> createServicePackage(@RequestBody @Valid ServicePKG servicePKG) {
      try {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("package", packageService.create(servicePKG)))
                         .message("package created")
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

   @GetMapping("/get_package/{id}")
   public ResponseEntity<Response> getServiceById(@PathVariable("id") Long servicePKGId) {
      try {
         ServicePKG servicePKG = packageService.getPackageById(servicePKGId);

         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("package", servicePKG))
                         .message("packages retrieved")
                         .status(OK)
                         .statusCode(OK.value())
                         .build()
         );
      } catch (EntityNotFoundException e) {
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
}