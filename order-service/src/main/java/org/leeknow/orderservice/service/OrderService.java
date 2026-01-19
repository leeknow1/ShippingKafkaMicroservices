package org.leeknow.orderservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.order.dto.OrderWithPaymentDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.orderservice.client.PaymentClient;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.entity.Order;
import org.leeknow.orderservice.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.leeknow.orderservice.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderKafkaService kafkaService;
    private final PaymentClient client;

    public void save(OrderDTO orderDTO) {
        Order order = mapToEntity(orderDTO);
        Order saved = repository.save(order);

        OrderCreatedDTO createdDTO = mapToCreatedDTO(saved);
        kafkaService.sendOrderCreated(createdDTO);
    }

    public OrderWithPaymentDTO find(int id) {
        Optional<Order> order = repository.findById(id);

        if (order.isPresent()) {
            new OrderWithPaymentDTO();
            OrderWithPaymentDTO orderWithPaymentDTO;
            try {
                PaymentDTO paymentByOrder = client.getPaymentByOrder(id);
                orderWithPaymentDTO = mapToOrderWithPayment(order.get(), paymentByOrder);
            } catch (FeignException.NotFound e) {
                //logs
                orderWithPaymentDTO = mapToOrderWithPayment(order.get(), null);
            }
            return orderWithPaymentDTO;
        }
        return null;
    }
}
