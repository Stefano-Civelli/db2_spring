package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.ActivationScheduleService;
import it.polimi.db2_spring.entities.ActivationSchedule;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/activation")
@RequiredArgsConstructor
public class ActivationScheduleController {

    private final ActivationScheduleService activationService;

    @PostMapping("/place_activation")
    public ResponseEntity<Response> createActivationSchedule(@RequestBody @Valid ActivationSchedule activationSchedule) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("user", activationService.create(activationSchedule)))
                        .message("user created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
}
