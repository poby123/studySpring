package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.kafka.KafkaProducer;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class KafkaController {
    
    private final KafkaProducer kafkaProducer;

    @GetMapping("/kafka")
    public String sendMessage(@RequestParam("messasge") String message) {
        kafkaProducer.sendMessage("myTopic", "message key my-topic", "test message" + LocalDateTime.now());
        return "Success Sending!";
    }
    
}
