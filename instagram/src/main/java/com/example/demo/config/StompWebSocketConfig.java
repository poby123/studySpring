package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer{
    

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // WebSocket or SockJS client가 웹 소켓 핸드쉐이크 커넥션을 생성할 경로.
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://wj-code-server.com:8080")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // STOMP 메시지의 destination 헤더가 "/pub"로 시작하면 @Controller의 @MessageMapping 메서드로 라우팅된다.
        registry.setApplicationDestinationPrefixes("/pub");

        // 내장된 메세지 브로커를 사용하며, SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 Client에게 메세지를 전달하는 간단한 작업을 수행
        registry.enableSimpleBroker("/sub");
    }
}
