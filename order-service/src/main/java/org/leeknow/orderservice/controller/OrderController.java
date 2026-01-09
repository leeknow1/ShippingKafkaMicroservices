package org.leeknow.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        //TODO service.find(id);
        return ResponseEntity.ok().build();
    }
}
