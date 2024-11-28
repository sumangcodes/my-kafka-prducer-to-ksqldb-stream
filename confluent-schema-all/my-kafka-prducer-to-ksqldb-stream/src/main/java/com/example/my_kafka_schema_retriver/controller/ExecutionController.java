package com.example.my_kafka_schema_retriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.my_kafka_schema_retriver.producer.AvroProducer;

@RestController
public class ExecutionController {

    private final AvroProducer producer;

    @Autowired
    public ExecutionController(AvroProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/execute")
    public String execute() {
        System.out.println("we are getting hiiiiiiiiiiiiiiiiiiiiittttttttttt");
        producer.sendEvent();
        return "Event executed";
    }
}