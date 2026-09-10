package com.springMVC.demo.springbasicLearning.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(@Qualifier("creditService") PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    /* 
    public void placeOrder() {

        paymentService.processPayment();

        System.out.println("Order Completed");
    }
    */
   @PostConstruct
    public void init() {
        placeOrder();

    }

    public void placeOrder() {
        paymentService.processPayment();
        System.out.println("Order Completed");
    }
}
