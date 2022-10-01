package com.example.demo.domain.member.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberFollow;
import com.example.demo.domain.member.repositoy.MemberFollowRepository;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberFollowRepository memberFollowRepository;
    
    // public String loginMember(LoginRequestDto request){
    //     Member member = memberRepository.findByUsername(request.getUsername()).orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_MISMATCH));

    //     if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
    //         throw new BusinessException(ErrorCode.ACCOUNT_MISMATCH);
    //     }

    //     return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    // }


    // @Transactional
    // public Member joinMember(SignupRequest request) {
    //     Member member = request.toEntity(passwordEncoder);

    //     if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
    //         throw new UsernameDuplicateException();
    //     }

    //     member = memberRepository.save(member);

    //     return member;
    // }


    @Transactional
    public void follow(Member from, String username){

        Member to = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
        List<MemberFollow> follows = memberFollowRepository.findByMemberAndFollow(from, to);
        
        if(follows.size() == 0){
            MemberFollow.doFollow(from, to);
        }
        else{
            MemberFollow.unFollow(follows.get(0));
        }
    }


    public Optional<Member> getMember(String username){
        return memberRepository.findByUsername(username);
    }


    @Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		return memberRepository.findByUsername(username)
			.map(this::createUserDetails)
			.orElseThrow(() -> new UsernameNotFoundException("일치하는 계정이 없습니다"));
	}

	private UserDetails createUserDetails(Member member) {
		final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRoles().toString());
		// TOKEN, AUTHENTICATION 에 넣을 값 (ex. username, id)
		return new User(
			String.valueOf(member.getId()),
			member.getPassword(),
			Collections.singleton(grantedAuthority));
	}

}
