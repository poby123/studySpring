package com.example.demo.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;

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
