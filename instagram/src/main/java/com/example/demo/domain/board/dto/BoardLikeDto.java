package com.example.demo.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardLikeDto {
    private Long boardId;
    private Long numberOfLikes;
    private boolean isCurrentMemberLike;

    @QueryProjection
    public BoardLikeDto(Long boardId, Long numberOfLikes, boolean isCurrentMemberLike) {
        this.boardId = boardId;
        this.numberOfLikes = numberOfLikes;
        this.isCurrentMemberLike = isCurrentMemberLike;
    }
}
