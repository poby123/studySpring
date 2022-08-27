package com.example.demo.domain.board.dto;

import com.example.demo.domain.member.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentViewDto {
    private MemberBoardViewDto member;
    private String content;

    @QueryProjection
    public CommentViewDto(Member member, String content) {
        this.member = new MemberBoardViewDto(member);
        this.content = content;
    }
}
