package org.leeknow.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.dto.OrderCreatedDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaService {

    private final KafkaTemplate<String, Object> template;

    public void sendOrderCreated(OrderCreatedDTO dto) {
        template.send("order.created", dto.getOrderId(), dto);
    }
}
