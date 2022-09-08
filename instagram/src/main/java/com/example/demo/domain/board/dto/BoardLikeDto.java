package com.example.demo.domain.board.dto;

import lombok.Data;

@Data
public class BoardLikeDto {
    private Long boardId;
    private int numberOfLikes;
    private boolean isCurrentMemberLike;
    
    public BoardLikeDto(Long boardId, int numberOfLikes, boolean isCurrentMemberLike) {
        this.boardId = boardId;
        this.numberOfLikes = numberOfLikes;
        this.isCurrentMemberLike = isCurrentMemberLike;
    }
}
