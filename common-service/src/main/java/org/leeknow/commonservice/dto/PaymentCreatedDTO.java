package org.leeknow.commonservice.dto;

import lombok.Data;

@Data
public class PaymentCreatedDTO {

    private String paymentId;
    private String status;
    private Integer orderId;
    private Integer userId;
}
