package com.example.demo.domain.board.dto;

import com.example.demo.domain.member.dto.MemberBoardViewDto;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentViewDto {
    private Long boardId;
    private MemberBoardViewDto member;
    private String content;

    @QueryProjection
    public CommentViewDto(Long boardId, String content, String username, String userProfileImage) {
        this.boardId = boardId;
        this.member = new MemberBoardViewDto(username, userProfileImage);
        this.content = content;
    }
}
