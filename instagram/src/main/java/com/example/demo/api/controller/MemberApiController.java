package com.example.demo.api.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.member.dto.MemberDto.MemberProfileViewDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {
    
    private final MemberRepository memberRepository;

    @GetMapping("/member/{memberUsername}")
    public String getMember(@PathVariable("memberUsername") String username, Model model) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
        MemberProfileViewDto dto = MemberProfileViewDto.of(member);
        model.addAttribute("member", dto);

        return "member/profile";
    }
}
