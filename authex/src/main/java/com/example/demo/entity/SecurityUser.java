package com.example.demo.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class SecurityUser extends User{
    
    private Member member;

    public SecurityUser(Member member){
        super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;

        log.info("SecurityUser member.username = {}", member.getUsername());
        log.info("SecurityUser member.password = {}", member.getPassword());
        log.info("SecurityUser member.role = {}", member.getRole().toString());
    }

}
