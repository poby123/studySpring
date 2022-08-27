package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberBoardViewDto {
    private String username;
    private String image;

    @QueryProjection
    public MemberBoardViewDto(String username, String image){
        this.username = username;
        this.image = image;
    }

    public MemberBoardViewDto(Member member){
        this.username = member.getUsername();
        this.image = member.getImage();
    }

    public static MemberBoardViewDto of(Member member) {
        MemberBoardViewDto ret = new MemberBoardViewDto();
        ret.setUsername(member.getUsername());
        ret.setImage(member.getImage());

        return ret;
    }
}