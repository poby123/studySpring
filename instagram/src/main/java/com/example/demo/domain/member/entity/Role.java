package com.example.demo.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_MANAGER("매니저"),
    ROLE_MEMBER("일반사용자");
    
    private final String description;
}
