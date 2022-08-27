package com.example.demo.domain.board.dto;

import com.example.demo.domain.member.dto.MemberBoardViewDto;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardLikeDto {

    private Long boardId;
    private MemberBoardViewDto member;

    @QueryProjection
    public BoardLikeDto(Long boardId, String username, String image){
        this.boardId = boardId;
        this.member = new MemberBoardViewDto(username, image);
    }
}
