package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository{
    Optional<Member> findByUsername(String username);
}
