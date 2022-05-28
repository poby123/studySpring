package com.example.demo.repository;

import com.example.demo.dto.MemberDto.MemberProfileViewDto;

public interface CustomMemberRepository {
    MemberProfileViewDto findByUsernameForProfileDto(String username);
}
