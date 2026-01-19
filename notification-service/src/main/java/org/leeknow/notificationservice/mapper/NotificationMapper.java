package org.leeknow.notificationservice.mapper;

import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.notificationservice.entity.Notification;
import org.leeknow.notificationservice.enums.NotificationStatus;

public class NotificationMapper {

    public static Notification mapToEntity(PaymentCreatedDTO dto) {
        Notification notification = new Notification();
        notification.setUserId(dto.getUserId());
        notification.setOrderId(dto.getOrderId());
        notification.setNotificationStatus(NotificationStatus.UNREAD);
        return notification;
    }
}
