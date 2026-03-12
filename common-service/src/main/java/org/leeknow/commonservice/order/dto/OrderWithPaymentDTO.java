package org.leeknow.commonservice.order.dto;

import lombok.Data;
import org.leeknow.commonservice.order.enums.OrderStatus;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.commonservice.user.dto.UserInfoDTO;

import java.time.LocalDateTime;

@Data
public class OrderWithPaymentDTO {

    private OrderStatus orderStatus;

    private Integer itemId;

    private Integer amount;

    private LocalDateTime created;

    private PaymentDTO payment;

    private UserInfoDTO userInfo;
}
