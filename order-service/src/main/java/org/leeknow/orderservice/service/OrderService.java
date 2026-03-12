package org.leeknow.orderservice.service;

import org.leeknow.commonservice.order.dto.OrderCreatedDTO;
import org.leeknow.commonservice.order.dto.OrderWithPaymentDTO;
import org.leeknow.commonservice.payment.dto.PaymentDTO;
import org.leeknow.commonservice.user.dto.UserInfoDTO;
import org.leeknow.orderservice.client.PaymentClient;
import org.leeknow.orderservice.client.UserClient;
import org.leeknow.orderservice.dto.OrderDTO;
import org.leeknow.orderservice.entity.Order;
import org.leeknow.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.leeknow.orderservice.mapper.OrderMapper.*;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderKafkaService kafkaService;
    private final PaymentClient paymentClient;
    private final UserClient userClient;
    private final AsyncTaskExecutor executor;

    public OrderService(OrderRepository repository,
                        OrderKafkaService kafkaService,
                        PaymentClient paymentClient,
                        UserClient userClient,
                        @Qualifier("ioExecutor") AsyncTaskExecutor executor) {
        this.repository = repository;
        this.kafkaService = kafkaService;
        this.paymentClient = paymentClient;
        this.userClient = userClient;
        this.executor = executor;
    }

    public void save(OrderDTO orderDTO, int userId) {
        orderDTO.setUserId(userId);
        Order order = mapToEntity(orderDTO);
        Order saved = repository.save(order);

        OrderCreatedDTO createdDTO = mapToCreatedDTO(saved);
        kafkaService.sendOrderCreated(createdDTO);
    }

    public OrderWithPaymentDTO find(int id) {
        Order order = repository.findById(id).orElseThrow(RuntimeException::new); //TODO custom ex

        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String authHeader;
        if (attrs != null) {
            authHeader = attrs.getRequest().getHeader("Authorization");
        } else {
            authHeader = null;
        }

        CompletableFuture<PaymentDTO> paymentF = CompletableFuture
                .supplyAsync(() -> paymentClient.getPaymentByOrder(order.getOrderId()), executor)
                .orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(throwable -> {
                    System.out.println("PAYMENT SERVICE NOT WORKING"); throwable.printStackTrace(); return null;});

        CompletableFuture<UserInfoDTO> userF = CompletableFuture
                .supplyAsync(() -> userClient.getUserInfo(order.getUserId(), authHeader), executor)
                .orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(throwable -> {
                    System.out.println("USER SERVICE NOT WORKING"); throwable.printStackTrace(); return null;});

        UserInfoDTO userInfoDTO = userF.join();
        PaymentDTO paymentDTO = paymentF.join();

        return mapToOrderWithPayment(order, paymentDTO, userInfoDTO);
    }
}
