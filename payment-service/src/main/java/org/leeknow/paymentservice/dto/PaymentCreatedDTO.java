package org.leeknow.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentCreatedDTO {

    private String paymentId;

    private String status;

    private Integer orderId;
}
