package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.OptionalService;
import it.polimi.db2_spring.beans.PackageService;
import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.ServicePKG;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/optional_products")
@RequiredArgsConstructor
public class OptionalProductController {

    private final OptionalService optionalService;

    @GetMapping("/list")
    public ResponseEntity<Response> getPackages() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("optionals", optionalService.getOptionalList(0)))
                        .message("packages retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/create_product")
    public ResponseEntity<Response> createServicePackage(@RequestBody @Valid OptionalProduct optionalProduct) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("package", optionalService.create(optionalProduct)))
                        .message("package created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
}
