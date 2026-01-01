package org.leeknow.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        service.save(orderDTO);
        return ResponseEntity.ok().build();
    }
}
