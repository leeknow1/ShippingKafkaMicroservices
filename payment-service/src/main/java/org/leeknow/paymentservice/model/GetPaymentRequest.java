package org.leeknow.paymentservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetPaymentRequest", namespace = "http://leeknow.kz/soap/payment")
@Getter
@Setter
@NoArgsConstructor
public class GetPaymentRequest {

    @XmlElement(name = "paymentId", namespace = "http://leeknow.kz/soap/payment", required = true)
    private int paymentId;
}
