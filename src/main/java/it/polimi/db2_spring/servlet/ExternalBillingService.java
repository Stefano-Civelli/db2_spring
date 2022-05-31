package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.beans.AlertService;
import it.polimi.db2_spring.beans.OrdersService;
import it.polimi.db2_spring.beans.UserService;
import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Random;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

//pretends to be the interface of the external billing service
@RestController
@RequestMapping("/billing_service")
@RequiredArgsConstructor
public class ExternalBillingService {
   private final OrdersService ordersService;
   private final UserService userService;
   private final AlertService alertService;

   @PostMapping("/check_payment")
   public ResponseEntity<Response> checkPaymentInfo(@RequestBody @Valid Orders order) {
      System.out.println("check payment order ID: " + order.getId());
      Orders dbOrder;
      boolean paymentOutcome = false;

      try {
         dbOrder = ordersService.getById(order.getId());
         paymentOutcome = isPaymentInfoOk(dbOrder);

         Users orderOwner = dbOrder.getUser();
         if(!paymentOutcome) {

            //is true if need to create a new alert
            if(userService.incrementFailedPaymentsAndCheckForAlert(orderOwner)) {
               alertService.create(new Alert(null, dbOrder.getTotalValue(), dbOrder.getCreationTime(), orderOwner));
            }
         }

         return ResponseEntity.ok(
                 Response.builder()
                         .timeStamp(now())
                         .data(Map.of("isPaymentInfoOk", paymentOutcome))
                         .message("payment info checked")
                         .status(OK)
                         .statusCode(OK.value())
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

   //questa merda andrebbe nel bean
   private Boolean isPaymentInfoOk(Orders order) throws CredentialsException {
      if(order.getIsRejected() != null && order.getIsRejected().equals(Boolean.FALSE))
         throw new CredentialsException("This order has already been payed");
      Random rand = new Random();
      int n = rand.nextInt(2);
      if(n == 1) {
         order.setIsRejected(Boolean.FALSE);
         ordersService.update(order);
         return Boolean.TRUE;
      }
      else {
         order.setIsRejected(Boolean.TRUE);
         ordersService.update(order);
         order.getUser().setIsInsolvent(Boolean.TRUE);
         userService.update(order.getUser());
         return Boolean.FALSE;
      }
   }
}
