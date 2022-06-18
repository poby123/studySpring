package com.example.demo.config.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.template.test-my-topic}", containerFactory = "myTopicKafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMeJJTopic(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey) throws Exception {
        log.info("Topic: [my-topic] messageKey Message: [" + messageKey + "]");
        log.info("Topic: [my-topic] Received Message: [" + message + "] from partition: [" + partition + "]");
    }
}
