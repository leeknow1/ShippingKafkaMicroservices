package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.mapper.PaymentMapper;
import org.leeknow.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentDTO findByOrderId(int id) {
        Optional<Payment> payment = paymentRepository.findByOrderId(id);
        return payment.map(PaymentMapper::mapToDTO).orElse(null);
    }
}
