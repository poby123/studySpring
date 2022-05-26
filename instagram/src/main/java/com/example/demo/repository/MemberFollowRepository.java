package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberFollow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long>{
    public List<MemberFollow> findByMemberAndFollow(Member from, Member to);
}
