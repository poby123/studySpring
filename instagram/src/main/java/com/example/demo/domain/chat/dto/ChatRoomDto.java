package com.example.demo.domain.chat.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class ChatRoomDto {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    
    public static ChatRoomDto create(String name){
        ChatRoomDto room = new ChatRoomDto();

        room.setRoomId(UUID.randomUUID().toString());
        room.setName(name);

        return room;
    }
}
