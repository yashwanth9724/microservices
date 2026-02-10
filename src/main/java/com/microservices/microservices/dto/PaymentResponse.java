package com.microservices.microservices.dto;

public record PaymentResponse(
        Long id,
        String orderId,
        Double amount,
        String status
) {}
