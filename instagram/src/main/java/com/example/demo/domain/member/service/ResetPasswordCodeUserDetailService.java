package com.example.demo.domain.member.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.redis.ResetPasswordCode;
import com.example.demo.domain.member.exception.PasswordResetFailException;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.example.demo.domain.member.repositoy.redis.ResetPasswordCodeRedisRepository;
import com.example.demo.global.exception.ErrorCode;
import com.example.demo.global.exception.types.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResetPasswordCodeUserDetailService implements UserDetailsService {

	private final ResetPasswordCodeRedisRepository resetPasswordCodeRedisRepository;
	private final MemberRepository memberRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		return resetPasswordCodeRedisRepository.findByUsername(username)
			.map(this::createUserDetails)
			.orElseThrow(PasswordResetFailException::new);
	}

	private UserDetails createUserDetails(ResetPasswordCode resetPasswordCode) {
		Member member = memberRepository.findByUsername(resetPasswordCode.getUsername())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
		final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().toString());
		return new User(
			String.valueOf(member.getId()),
			resetPasswordCode.getCode(),
			Collections.singleton(grantedAuthority));
	}

}
