package com.example.demo.global.config.kafka;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domain.chat.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    // @KafkaListener(topics = "${spring.kafka.template.test-my-topic}", containerFactory = "myTopicKafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMyTopic(@Payload ChatMessageDto message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey) throws Exception {
        log.info("Topic: [my-topic] messageKey Message: [" + messageKey + "]");
        log.info("Topic: [my-topic] Received Message: [" + message.getMessage() + "] from partition: [" + partition + "]");

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
