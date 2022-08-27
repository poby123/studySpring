package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.MemberFollow;

import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberFollowDto {
    
    @Data
    @NoArgsConstructor
    public static class FollowerDto{
        private MemberBoardViewDto member;

        FollowerDto(MemberFollow follow){
            this.member = MemberBoardViewDto.of(follow.getMember());
        }
    }

    @Data
    @NoArgsConstructor
    public static class FollowingDto{
        private MemberBoardViewDto member;

        FollowingDto(MemberFollow follow){
            this.member = MemberBoardViewDto.of(follow.getFollow());
        }
    }
}
