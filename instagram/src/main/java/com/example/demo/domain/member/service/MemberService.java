package com.example.demo.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> getMember(String username){
        return memberRepository.findByUsername(username);
    }
}
