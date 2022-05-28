package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.dto.MemberDto.MemberProfileViewDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberProfileViewDtoFetchTest(){
        MemberProfileViewDto dto = memberRepository.findByUsernameForProfileDto("poby123");
        assertEquals("하이디", dto.getName());
        assertEquals("12345@naver.com", dto.getEmail());

    }
}
