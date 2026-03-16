package org.leeknow.paymentservice.mapper;

import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.commonservice.payment.enums.PaymentStatus;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.model.GetPaymentResponse;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class PaymentMapper {

    public static Payment mapToEntity(OrderCreatedDTO dto) {
        Payment payment = new Payment();
        payment.setCreated(new Timestamp(System.currentTimeMillis()));
        payment.setOrderId(Integer.valueOf(dto.getOrderId()));
        payment.setUserId(dto.getUserId());
        payment.setStatus(PaymentStatus.NOT_PROCESSED);
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

    public static GetPaymentResponse mapToXmlDTO(Payment payment) {
        GetPaymentResponse getPaymentResponse = new GetPaymentResponse();
        getPaymentResponse.setStatus(payment.getStatus().name());
        getPaymentResponse.setCreated(convertDateToXMLGregorianCalendar(payment.getCreated()));
        getPaymentResponse.setCompleted(convertDateToXMLGregorianCalendar(payment.getCompleted()));
        return  getPaymentResponse;
    }

    private static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Timestamp date) {
        if (date == null) return null;

        GregorianCalendar calendar = GregorianCalendar.from(date.toInstant().atZone(ZoneId.systemDefault()));
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (Exception e) {
            //Exception handler
        }
        return null;
    }
}
