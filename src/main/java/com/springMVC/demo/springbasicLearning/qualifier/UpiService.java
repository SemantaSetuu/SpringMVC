package com.springMVC.demo.springbasicLearning.qualifier;

import org.springframework.stereotype.Service;

@Service
public class UpiService implements PaymentService {

    @Override
    public void processPayment() {
        System.out.println("Payment Processed By UPI");
    }
}