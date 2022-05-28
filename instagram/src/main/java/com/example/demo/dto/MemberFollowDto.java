package com.example.demo.dto;

import com.example.demo.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.entity.MemberFollow;

import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberFollowDto {
    
    @Data
    @NoArgsConstructor
    public static class DefaultMemberFollowDto{
        private MemberBoardViewDto member;

        DefaultMemberFollowDto(MemberFollow follow){
            this.member = MemberBoardViewDto.of(follow);
        }
    }
}
