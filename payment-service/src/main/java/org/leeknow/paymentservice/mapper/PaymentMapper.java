package org.leeknow.paymentservice.mapper;

import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.paymentservice.entity.Payment;

import java.sql.Timestamp;

public class PaymentMapper {

    public static Payment mapToEntity(OrderCreatedDTO dto) {
        Payment payment = new Payment();
        payment.setCreated(new Timestamp(System.currentTimeMillis()));
        payment.setOrderId(Integer.valueOf(dto.getOrderId()));
        payment.setUserId(dto.getUserId());
        return payment;
    }

    public static PaymentCreatedDTO mapToCreatedDTO(Payment payment) {
        PaymentCreatedDTO dto = new PaymentCreatedDTO();
        dto.setPaymentId(payment.getPaymentId().toString());
        dto.setStatus(payment.getStatus().name());
        dto.setOrderId(payment.getOrderId());
        dto.setUserId(payment.getUserId());
        return dto;
    }

    public static PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setStatus(payment.getStatus());
        dto.setCreated(payment.getCreated());
        dto.setCompleted(payment.getCompleted());
        return dto;
    }
}
