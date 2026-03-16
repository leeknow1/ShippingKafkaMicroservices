package org.leeknow.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.order.dto.OrderWithPaymentDTO;
import org.leeknow.commonservice.order.enums.OrderStatus;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO,
                                       @AuthenticationPrincipal Jwt jwt) {
        service.save(orderDTO, Integer.parseInt(jwt.getSubject()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        OrderWithPaymentDTO orderWithPaymentDTO = service.find(id);
        if (orderWithPaymentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderWithPaymentDTO);
    }

    @PostMapping("/{id}/update-status")
    public ResponseEntity<?> updateStatus(@PathVariable int id, @RequestBody OrderStatus orderStatus) {
        service.updateStatus(id, orderStatus);
        return ResponseEntity.ok().build();
    }
}
