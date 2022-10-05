package com.example.demo.domain.member.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberFollow;

public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long>{
    public List<MemberFollow> findByMemberAndFollow(Member from, Member to);
}
