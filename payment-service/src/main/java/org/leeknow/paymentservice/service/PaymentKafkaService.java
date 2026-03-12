package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.commonservice.payment.enums.PaymentStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentKafkaService {

    private final KafkaTemplate<String, Object> template;
    private final PaymentService paymentService;

    @KafkaListener(topics = "order.created", groupId = "payment-service")
    public void receiveOrderCreated(OrderCreatedDTO dto) {
        System.out.println("received an order -> " + dto.toString());

        PaymentCreatedDTO createdDTO = paymentService.processOrder(dto);

        sendCompletedPayment(createdDTO);
    }

    private void sendCompletedPayment(PaymentCreatedDTO payment) {
        String topic;
        if (PaymentStatus.valueOf(payment.getStatus()) == PaymentStatus.SUCCESS) {
            topic = "payment.success";
        } else {
            topic = "payment.failed";
        }
        template.send(topic, payment.getPaymentId(), payment);
    }
}
