package org.leeknow.orderservice.dto;

import lombok.Data;

@Data
public class OrderCreatedDTO {
    private String orderId;
    private Integer userId;
    private Integer itemId;
    private Integer amount;
}
