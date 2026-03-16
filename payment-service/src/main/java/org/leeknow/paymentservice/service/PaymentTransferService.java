package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.payment.enums.PaymentStatus;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PaymentTransferService {

    private final PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateOrderStatus(Payment payment) {
        makePayment(payment);
        paymentRepository.save(payment);
    }

    private void makePayment(Payment payment) {
        boolean success = ThreadLocalRandom.current().nextInt(100) < 95;
        if (success) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        payment.setCompleted(new Timestamp(System.currentTimeMillis()));
    }
}
