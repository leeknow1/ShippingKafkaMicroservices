package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.dto.OrderCreatedDTO;
import org.leeknow.paymentservice.dto.PaymentCreatedDTO;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.enums.PaymentStatus;
import org.leeknow.paymentservice.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

import static org.leeknow.paymentservice.mapper.PaymentMapper.mapToCreatedDTO;
import static org.leeknow.paymentservice.mapper.PaymentMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class PaymentKafkaService {

    private final KafkaTemplate<String, Object> template;
    private final PaymentRepository repository;

    @KafkaListener(topics = "order.created", groupId = "payment-service")
    public void receiveOrderCreated(OrderCreatedDTO dto) {
        System.out.println("received an order -> " + dto.toString());

        Payment payment = mapToEntity(dto);

        makePayment(payment);

        payment = repository.save(payment);

        PaymentCreatedDTO createdDTO = mapToCreatedDTO(payment);

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

    private void makePayment(Payment payment) {
        boolean success = ThreadLocalRandom.current().nextInt(100) < 95;

        if (success) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        payment.setCompleted(new Timestamp(System.currentTimeMillis()));
    }
}
