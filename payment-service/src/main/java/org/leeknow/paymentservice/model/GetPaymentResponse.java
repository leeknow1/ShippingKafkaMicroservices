package org.leeknow.paymentservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://leeknow.kz/soap/payment", name = "GetPaymentResponse")
@Getter
@Setter
@NoArgsConstructor
public class GetPaymentResponse {

    private String status;

    private XMLGregorianCalendar created;

    private XMLGregorianCalendar completed;
}
