package com.springMVC.demo.springbasicLearning.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentService() {
        System.out.println("PaymentService Bean Created");
    }

    public void processPayment() {
        System.out.println("Payment Processed");
    }
}