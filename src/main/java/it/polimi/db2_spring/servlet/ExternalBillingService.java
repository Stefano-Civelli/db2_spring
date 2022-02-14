package it.polimi.db2_spring.servlet;

import it.polimi.db2_spring.utility.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

//pretends to be the interface of the external billing service
@RestController
@RequestMapping("/billing_service")
@RequiredArgsConstructor
public class ExternalBillingService {

   @PostMapping("/check_payment")
   public ResponseEntity<Response> checkPaymentInfo() {
      return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .data(Map.of("isPaymentInfoOk", isPaymentInfoOk()))
                      .message("payment info checked")
                      .status(OK)
                      .statusCode(OK.value())
                      .build()
      );
   }

   private Boolean isPaymentInfoOk() {
      Random rand = new Random();
      int n = rand.nextInt(2);
      if(n == 1)
         return Boolean.TRUE;
      else
          return Boolean.FALSE;
   }

}
