package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.BoardLike;
import com.example.demo.domain.member.dto.MemberDto.MemberBoardViewDto;

import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardLikeDto {

    @Data
    @NoArgsConstructor
    public static class BoardLikeForBoardDto{
        private MemberBoardViewDto memberBoardViewDto;

        public static BoardLikeForBoardDto of(BoardLike boardLike){            
            BoardLikeForBoardDto ret = new BoardLikeForBoardDto();
            ret.setMemberBoardViewDto(MemberBoardViewDto.of(boardLike.getMember()));

            return ret;
        }
    }
}
