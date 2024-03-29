package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.ServicesService;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesService servicesService;

    @GetMapping("/list")
    public ResponseEntity<Response> getPackages() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("services", servicesService.getServicesList(0)))
                        .message("services retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
