package org.leeknow.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.notificationservice.entity.Notification;
import org.leeknow.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.leeknow.notificationservice.mapper.NotificationMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class NotificationKafkaService {

    private final NotificationRepository repository;

    @KafkaListener(topics = {"payment.success", "payment.failed"}, groupId = "notification-service")
    private void receivePaymentResults(PaymentCreatedDTO createdDTO) {
        System.out.println("received an payment result -> " + createdDTO.toString());

        Notification notification = mapToEntity(createdDTO);

        repository.save(notification);
    }
}
