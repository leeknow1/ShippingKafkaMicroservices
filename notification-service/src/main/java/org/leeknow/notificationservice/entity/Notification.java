package org.leeknow.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.leeknow.notificationservice.enums.NotificationStatus;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private Integer userId;

    private Integer orderId;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;
}
