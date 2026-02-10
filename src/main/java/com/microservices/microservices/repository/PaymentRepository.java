package com.microservices.microservices.repository;

import com.microservices.microservices.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PaymentRepository  extends JpaRepository<Payment, Long> {
}
