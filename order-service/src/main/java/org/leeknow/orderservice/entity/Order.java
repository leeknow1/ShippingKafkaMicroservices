package org.leeknow.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.leeknow.orderservice.enums.OrderStatus;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private Integer userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Integer itemId;

    private Integer amount;

    private Timestamp created;
}
