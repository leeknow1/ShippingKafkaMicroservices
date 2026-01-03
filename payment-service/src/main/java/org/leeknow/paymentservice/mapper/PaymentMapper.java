package org.leeknow.paymentservice.mapper;

import org.leeknow.commonservice.dto.OrderCreatedDTO;
import org.leeknow.paymentservice.dto.PaymentCreatedDTO;
import org.leeknow.paymentservice.entity.Payment;

import java.sql.Timestamp;

public class PaymentMapper {

    public static Payment mapToEntity(OrderCreatedDTO dto) {
        Payment payment = new Payment();
        payment.setCreated(new Timestamp(System.currentTimeMillis()));
        payment.setOrderId(Integer.valueOf(dto.getOrderId()));
        return payment;
    }

    public static PaymentCreatedDTO mapToCreatedDTO(Payment payment) {
        PaymentCreatedDTO dto = new PaymentCreatedDTO();
        dto.setPaymentId(payment.getPaymentId().toString());
        dto.setStatus(payment.getStatus().name());
        dto.setOrderId(payment.getOrderId());
        return dto;
    }
}
