package org.leeknow.orderservice.dto;

import lombok.Data;
import org.leeknow.orderservice.enums.OrderStatus;

@Data
public class OrderDTO {

    public Integer userId;
    public Integer itemId;
    public Integer amount;
}
