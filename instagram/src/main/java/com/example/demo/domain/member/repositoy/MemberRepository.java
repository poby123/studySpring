package com.example.demo.domain.member.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository{
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
