package org.leeknow.paymentservice.endpoint;

import lombok.RequiredArgsConstructor;
import org.leeknow.paymentservice.model.GetPaymentRequest;
import org.leeknow.paymentservice.model.GetPaymentResponse;
import org.leeknow.paymentservice.service.PaymentService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PaymentEndpoint {

    public static final String NAMESPACE_URI = "http://leeknow.kz/soap/payment";

    private final PaymentService paymentService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPaymentRequest")
    public GetPaymentResponse getXmlPaymentDTO(@RequestPayload GetPaymentRequest getPaymentRequest) {
        return paymentService.findById(getPaymentRequest.getPaymentId());
    }
}
