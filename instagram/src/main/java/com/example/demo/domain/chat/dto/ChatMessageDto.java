package com.example.demo.domain.chat.dto;


import lombok.Data;

@Data
public class ChatMessageDto {
    private String roomId;
    private String writer;
    private String message;
    private String timestamp;

    @Override
    public String toString(){
        return "[" + writer + "]: " + message + "(" + timestamp + ")";
    }
}