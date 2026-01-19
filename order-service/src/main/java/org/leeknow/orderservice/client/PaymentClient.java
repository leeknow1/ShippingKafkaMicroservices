package org.leeknow.orderservice.client;

import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/order/{id}")
    PaymentDTO getPaymentByOrder(@PathVariable int id);
}
