package com.example.demo.global.config.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberDoesNotExistException;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUtil {

	private final MemberRepository memberRepository;

	public Long getLoginMemberIdOrNull() {
		try {
			final String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			return Long.valueOf(memberId);
		} catch (Exception e) {
			return -1L;
		}
	}

	public Long getLoginMemberId() {
		try {
			final String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			return Long.valueOf(memberId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public Member getLoginMember() {
		try {
			final Long memberId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
			return memberRepository.findById(memberId).orElseThrow(MemberDoesNotExistException::new);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}