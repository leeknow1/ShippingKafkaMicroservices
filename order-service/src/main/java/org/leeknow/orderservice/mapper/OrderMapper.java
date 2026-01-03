package org.leeknow.orderservice.mapper;

import org.leeknow.commonservice.dto.OrderCreatedDTO;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.entity.Order;
import org.leeknow.orderservice.enums.OrderStatus;

import java.sql.Timestamp;

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
        order.setCreated(new Timestamp(System.currentTimeMillis()));
        return order;
    }
}
