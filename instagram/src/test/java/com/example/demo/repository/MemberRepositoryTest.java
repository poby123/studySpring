package com.example.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.member.repositoy.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberProfileViewDtoFetchTest(){

    }
}
