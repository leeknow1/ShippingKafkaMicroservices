package org.leeknow.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.leeknow.commonservice.payment.enums.PaymentStatus;

import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Timestamp created;

    private Timestamp completed;

    @Column(unique = true)
    private Integer orderId;

    private Integer userId;
}
