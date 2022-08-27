package com.example.demo.domain.board.dto;

import com.example.demo.domain.member.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardLikeDto {

    private Long boardId;
    private MemberBoardViewDto member;

    @QueryProjection
    public BoardLikeDto(Long boardId, Member member){
        this.boardId = boardId;
        this.member = new MemberBoardViewDto(member);
    }
}
