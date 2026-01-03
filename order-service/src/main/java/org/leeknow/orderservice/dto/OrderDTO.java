package org.leeknow.orderservice.dto;

import lombok.Data;

@Data
public class OrderDTO {

    public Integer userId;
    public Integer itemId;
    public Integer amount;
}
