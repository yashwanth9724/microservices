package com.microservices.microservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

public class PaymentCreateRequest {

   @NotBlank
   @Getter
   @Setter
   public String orderId;

    @NotNull
    @Positive
    @Getter
    @Setter
    Double amount;


    @NotBlank(message = "Status is required")
    @Setter
    @Getter
    private String status;


}
