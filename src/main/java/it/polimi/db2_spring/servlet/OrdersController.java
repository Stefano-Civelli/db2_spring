package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.OrdersService;
import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

   private final OrdersService ordersService;

   @PostMapping("/place_order")
   public ResponseEntity<Response> placeOrder(@RequestBody @Valid Orders order) {

      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("user", ordersService.create(order)))
                      .message("user created")
                      .status(CREATED)
                      .statusCode(CREATED.value())
                      .build()
      );
   }


   @GetMapping("/list")
   public ResponseEntity<Response> getPackages(@RequestBody @Valid Users user) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("packages", ordersService.getOrdersList(user)))
                      .message("packages retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }







}