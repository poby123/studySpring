package com.example.demo.global.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.domain.chat.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, ChatMessageDto> kafkaTemplate;

    public void sendMessage(String topicName, String messageKey, ChatMessageDto message) {

        Message<ChatMessageDto> messageBuilder = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.MESSAGE_KEY, messageKey)
                .build();

        ListenableFuture<SendResult<String, ChatMessageDto>> future = kafkaTemplate.send(messageBuilder);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ChatMessageDto>>() {

            @Override
            public void onSuccess(SendResult<String, ChatMessageDto> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
