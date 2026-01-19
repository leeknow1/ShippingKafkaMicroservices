package org.leeknow.commonservice.order.dto;

import lombok.Data;
import org.leeknow.commonservice.order.enums.OrderStatus;
import org.leeknow.commonservice.payment.dto.PaymentDTO;

import java.time.LocalDateTime;

@Data
public class OrderWithPaymentDTO {

    private Integer userId;

    private OrderStatus orderStatus;

    private Integer itemId;

    private Integer amount;

    private LocalDateTime created;

    private PaymentDTO payment;
}
