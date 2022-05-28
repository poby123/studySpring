package com.example.demo.repository;

import static com.example.demo.entity.QMember.member;

import java.util.List;

import com.example.demo.dto.BoardDto.BoardViewDto;
import com.example.demo.dto.MemberDto.MemberProfileViewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberProfileViewDto findByUsernameForProfileDto(String username) {
        MemberProfileViewDto ret = 
            jpaQueryFactory
                .select(Projections.bean(MemberProfileViewDto.class, member.username, member.name, member.email, member.image, member.job, member.about))
                .from(member)
                .where(member.username.eq(username))
                .fetchFirst();


                // private List<BoardViewDto> boards;
                // private List<MemberBoardViewDto> followers;
                // private List<MemberBoardViewDto> followings;

        return ret;
    }

    public List<BoardViewDto> findBoardViewDto(){
        // jpaQueryFactory.select(Projections.bean(BoardViewDto.class, ))
        return null;
    }

}
