package it.polimi.db2_spring.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//pretends to be the interface of the external billing service
@RestController
@RequestMapping("/billing_service")
@RequiredArgsConstructor
public class ExternalBillingServiceStub {
}
