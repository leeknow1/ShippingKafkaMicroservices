package org.leeknow.orderservice.mapper;

import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.order.dto.OrderWithPaymentDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.commonservice.user.dto.UserInfoDTO;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.entity.Order;
import org.leeknow.commonservice.order.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderMapper {

    public static OrderCreatedDTO mapToCreatedDTO(Order saved) {
        OrderCreatedDTO createdDTO = new OrderCreatedDTO();
        createdDTO.setOrderId(saved.getOrderId().toString());
        createdDTO.setUserId(saved.getUserId());
        createdDTO.setAmount(saved.getAmount());
        createdDTO.setItemId(saved.getItemId());
        return createdDTO;
    }

    public static Order mapToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setAmount(dto.getAmount());
        order.setItemId(dto.getItemId());
        order.setCreated(LocalDateTime.now());
        return order;
    }

    public static OrderWithPaymentDTO mapToOrderWithPayment(Order order, PaymentDTO paymentDTO, UserInfoDTO userInfoDTO) {
        OrderWithPaymentDTO dto = new OrderWithPaymentDTO();
        dto.setOrderStatus(order.getOrderStatus());
        dto.setAmount(order.getAmount());
        dto.setItemId(order.getItemId());
        dto.setCreated(order.getCreated());
        dto.setPayment(paymentDTO);
        dto.setUserInfo(userInfoDTO);
        return dto;
    }
}
