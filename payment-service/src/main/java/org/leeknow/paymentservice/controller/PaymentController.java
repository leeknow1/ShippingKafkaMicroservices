package org.leeknow.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getPaymentByOrder(@PathVariable int id){
        PaymentDTO paymentDTO = service.findByOrderId(id);
        if (paymentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentDTO);
    }
}
