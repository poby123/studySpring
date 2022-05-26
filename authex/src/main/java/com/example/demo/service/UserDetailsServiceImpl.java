package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Member;
import com.example.demo.entity.SecurityUser;
import com.example.demo.repository.MemberRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        
        if (!findMember.isPresent()) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }

        log.info("loadUserByUsername member.username = {}", username);
        return new SecurityUser(findMember.get());
    }

}
