package com.microservices.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/payment/health")
    public String health() {
        return "Payment Service is healthy!";
    }
}
