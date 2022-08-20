package com.example.demo.domain.chat.repositoy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.chat.dto.ChatRoomDto;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDto> chatRoomDtoMap = new LinkedHashMap<>();

    
    public List<ChatRoomDto> findAllRooms(){
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDto findByRoomId(String id){
        return chatRoomDtoMap.get(id);
    }

    public ChatRoomDto createChatRoomDto(String name){
        ChatRoomDto room = ChatRoomDto.create(name);
        chatRoomDtoMap.put(room.getRoomId(), room);

        return room;
    }
}
