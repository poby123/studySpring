package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.MemberDto.SignupRequest;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberFollow;
import com.example.demo.entity.SecurityUser;
import com.example.demo.exception.types.UsernameDuplicateException;
import com.example.demo.repository.MemberFollowRepository;
import com.example.demo.repository.MemberRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberFollowRepository memberFollowRepository;

    @Transactional
    public Member joinMember(SignupRequest request) {
        Member member = request.toEntity(passwordEncoder);

        if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
            throw new UsernameDuplicateException();
        }

        member = memberRepository.save(member);

        return member;
    }


    @Transactional
    public void follow(Member from, String username){

        Member to = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

        if(memberFollowRepository.findByMemberAndFollow(from, to).size() == 0){
            MemberFollow.doFollow(from, to);
        }
    }


    public Optional<Member> getMember(String username){
        return memberRepository.findByUsername(username);
    }

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
