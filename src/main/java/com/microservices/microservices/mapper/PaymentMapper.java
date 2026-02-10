package com.microservices.microservices.mapper;

import com.microservices.microservices.dto.PaymentResponse;
import com.microservices.microservices.entity.Payment;

public class PaymentMapper {


    public static PaymentResponse toResponse(Payment payment) {

        return new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getAmount(), payment.getStatus().name());


    }
}
