package com.microservices.microservices.controller;

import com.microservices.microservices.dto.PaymentCreateRequest;
import com.microservices.microservices.dto.PaymentResponse;
import com.microservices.microservices.entity.Payment;
import com.microservices.microservices.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/health")
    public String health() {
        return "Payment Service is healthy!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createPayment(@RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody PaymentCreateRequest payment) {
        return paymentService.createPayment(idempotencyKey, payment);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

}