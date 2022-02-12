package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.PackageService;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

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




}
