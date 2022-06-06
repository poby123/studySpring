package com.example.demo.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final RedisTemplate<String,Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessageDto chatDTO){
        redisTemplate.convertAndSend(topic.getTopic(),chatDTO);
    }
}
