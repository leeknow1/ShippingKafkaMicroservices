package org.leeknow.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.orderservice.dto.OrderCreatedDTO;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.entity.Order;
import org.leeknow.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import static org.leeknow.orderservice.mapper.OrderMapper.mapToCreatedDTO;
import static org.leeknow.orderservice.mapper.OrderMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderKafkaService kafkaService;

    public void save(OrderDTO orderDTO) {
        Order order = mapToEntity(orderDTO);
        Order saved = repository.save(order);

        OrderCreatedDTO createdDTO = mapToCreatedDTO(saved);
        kafkaService.sendOrderCreated(createdDTO);
    }
}
