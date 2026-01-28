package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.mapper.PaymentMapper;
import org.leeknow.paymentservice.model.GetPaymentResponse;
import org.leeknow.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.SoapFaultException;

import java.util.Optional;

import static org.leeknow.paymentservice.mapper.PaymentMapper.mapToXmlDTO;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentDTO findByOrderId(int id) {
        Optional<Payment> payment = paymentRepository.findByOrderId(id);
        return payment.map(PaymentMapper::mapToDTO).orElse(null);
    }

    public GetPaymentResponse findById(int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return mapToXmlDTO(payment.get());
        }
        //TODO: messages
        throw new SoapFaultException("payment.not_found");
    }
}
