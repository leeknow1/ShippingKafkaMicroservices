package org.leeknow.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentCreatedDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.paymentservice.entity.Payment;
import org.leeknow.paymentservice.mapper.PaymentMapper;
import org.leeknow.paymentservice.model.GetPaymentResponse;
import org.leeknow.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.soap.SoapFaultException;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.leeknow.paymentservice.mapper.PaymentMapper.*;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentTransferService transferService;

    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

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

    @Transactional(rollbackFor = Exception.class)
    public PaymentCreatedDTO processOrder(OrderCreatedDTO dto) {
        String orderId = dto.getOrderId();

        ReentrantLock reentrantLock = locks.computeIfAbsent(orderId, k -> new ReentrantLock());

        boolean locked = false;

        try {
         locked = reentrantLock.tryLock(3, TimeUnit.SECONDS);
         if (!locked) {
             throw new RuntimeException("lock.no_lock_for_order");
         }

         Optional<Payment> existingPayment = paymentRepository.findByOrderId(Integer.parseInt(orderId));
         if (existingPayment.isPresent()) {
             return mapToCreatedDTO(existingPayment.get());
         }

         Payment payment = mapToEntity(dto);

         payment = paymentRepository.save(payment);

         transferService.updateOrderStatus(payment);

         return mapToCreatedDTO(payment);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("lock.thread_interrupted");
        } finally {
            if (locked) {
                reentrantLock.unlock();
            }
            if (!reentrantLock.isLocked() && !reentrantLock.hasQueuedThreads()) {
                locks.remove(orderId, reentrantLock);
            }
        }
    }
}
