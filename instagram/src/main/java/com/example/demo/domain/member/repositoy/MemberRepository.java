package com.example.demo.domain.member.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository{
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
