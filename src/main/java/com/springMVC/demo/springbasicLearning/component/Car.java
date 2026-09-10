package com.springMVC.demo.springbasicLearning.component;

import org.springframework.stereotype.Component;

@Component
public class Car{
    private final Engine engine;

    public Car(Engine engine){
        this.engine=engine;
        System.out.println("Car bean created");
    }

}