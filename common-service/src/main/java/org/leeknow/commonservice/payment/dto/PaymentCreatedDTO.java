package org.leeknow.commonservice.payment.dto;

import lombok.Data;

@Data
public class PaymentCreatedDTO {

    private String paymentId;
    private String status;
    private Integer orderId;
    private Integer userId;
}
