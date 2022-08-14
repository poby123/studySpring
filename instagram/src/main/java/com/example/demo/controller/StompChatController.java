package com.example.demo.controller;

import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.config.kafka.KafkaProducer;
import com.example.demo.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompChatController {
    
    private final KafkaProducer kafkaProducer;

    /*
     * Client가 SEND할 수 있는 경로
     * stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨 = "/pub/chat/enter"
     */

    @Value("${spring.kafka.template.test-my-topic}")
    private String topicName;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto message) {
        log.info("{}: {}님 입장", message.getRoomId(), message.getWriter());
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        message.setTimestamp(LocalDateTime.now(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Seoul"))).toString());

        // template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        kafkaProducer.sendMessage(topicName, message.getRoomId(), message);
    }


    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        log.info("Message : {}", message.getMessage());

        // template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        kafkaProducer.sendMessage(topicName, message.getRoomId(), message);
    }
}
