package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.OrdersService;
import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CreationException;
import it.polimi.db2_spring.utility.PriceInfoContainer;
import it.polimi.db2_spring.beans.PriceCalculatorService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

   private final OrdersService ordersService;
   private final PriceCalculatorService priceCalculator;

   @PostMapping("/place_order")
   public ResponseEntity<Response> placeOrder(@RequestBody @Valid Orders order) {
      order.setIsRejected(null);
      try {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("order", ordersService.create(order)))
                         .message("order created")
                         .status(CREATED)
                         .statusCode(CREATED.value())
                         .build()
         );
      } catch (CreationException e) {
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


   @GetMapping("/list/{username}") // can be instresting with $router.push() on frontend side
   public ResponseEntity<Response> getOrderList(@PathVariable("username") Users user) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("orders", ordersService.getOrdersList(user)))
                      .message("orders retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }


   @GetMapping("/list_rejected/{username}") //basta l'username dell'utente
   public ResponseEntity<Response> getRejectedOrderList(@PathVariable("username") Users user) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("orders", ordersService.getRejectedOrderList(user)))
                      .message("orders retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   @PostMapping("/calculate_price")
   public ResponseEntity<Response> getTotalValue(@RequestBody PriceInfoContainer priceInfoContainer) {
      try {
         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("totalCost", priceCalculator.getTotalValue(priceInfoContainer.getPeriod(), priceInfoContainer.getSelectedOptionalProducts())))
                         .message("cost of the order")
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

   @GetMapping("/activation_schedule/{username}") // can be instresting with $router.push() on frontend side
   public ResponseEntity<Response> getOrderActivationSchedule(@PathVariable("username") Users user) {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("orders", ordersService.getOrdersActivationSchedule(user)))
                      .message("orders retrieved")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }
}