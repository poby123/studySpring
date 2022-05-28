package com.example.demo.dto;

import com.example.demo.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.entity.BoardLike;

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
