package org.leeknow.commonservice.payment.dto;

import lombok.Data;
import org.leeknow.commonservice.payment.enums.PaymentStatus;

import java.sql.Timestamp;

@Data
public class PaymentDTO {

    private PaymentStatus status;

    private Timestamp created;

    private Timestamp completed;
}
