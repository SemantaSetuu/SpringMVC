package com.springMVC.demo.springbasicLearning.qualifier;

import org.springframework.stereotype.Service;

@Service
public class CreditService implements PaymentService {

    @Override
    public void processPayment() {
        System.out.println("Payment Processed By Credit Card");
    }
}