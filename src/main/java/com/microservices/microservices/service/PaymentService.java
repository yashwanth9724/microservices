package com.microservices.microservices.service;

import com.microservices.microservices.dto.PaymentCreateRequest;
import com.microservices.microservices.dto.PaymentResponse;
import com.microservices.microservices.entity.Payment;
import com.microservices.microservices.entity.PaymentStatus;
import com.microservices.microservices.exception.PaymentNotFoundException;
import com.microservices.microservices.mapper.PaymentMapper;
import com.microservices.microservices.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /*@Transactional
    public PaymentResponse createPayment(PaymentCreateRequest req) {
        Payment payment =
                Payment.builder().orderId(req.getOrderId())
                        .amount(req.getAmount())
                        .status(PaymentStatus.valueOf(req.getStatus()))
                        .build();

        Payment saved = paymentRepository.save(payment);
        return PaymentMapper.toResponse(saved);

    }*/

    @Transactional
    public PaymentResponse createPayment(String idempotencyKey,
                                         PaymentCreateRequest req) {
        // 1?Check if payment with the same idempotency key exists
        Optional<Payment> existing =
                paymentRepository.findByIdempotencyKey(idempotencyKey);

        if (existing.isPresent()) {
            return PaymentMapper.toResponse(existing.get());
        }

        // New payment
        Payment payment = Payment.builder()
                .orderId(req.getOrderId())
                .amount(req.getAmount())
                .status(PaymentStatus.valueOf(req.getStatus()))
                .idempotencyKey(idempotencyKey)
                .build();

        Payment saved = paymentRepository.save(payment);
        return PaymentMapper.toResponse(saved);
    }


    @Transactional
        public PaymentResponse getById(Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        return PaymentMapper.toResponse(p);

        }

}
