package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
}
