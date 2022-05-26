package com.example.demo.service.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.dto.MemberDto.SignupRequest;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberFollow;
import com.example.demo.exception.types.UsernameDuplicateException;
import com.example.demo.repository.MemberFollowRepository;
import com.example.demo.service.UserDetailsServiceImpl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MemberServiceTest {

    @Autowired
    UserDetailsServiceImpl memberService;

    @Autowired
    MemberFollowRepository memberFollowRepository;

    // @Before
    public void testid등록() {
        SignupRequest req = new SignupRequest();

        req.setUsername("testid");
        req.setName("name");
        req.setPassword("password");
        req.setEmail("1234@naver.com");

        memberService.joinMember(req);
    }

    @Test(expected = UsernameDuplicateException.class)
    @Ignore
    public void usernameDuplicatedExceptionTest() {
        SignupRequest req = new SignupRequest();

        req.setUsername("testid");
        req.setName("name");
        req.setPassword("password");
        req.setEmail("1234@naver.com");

        memberService.joinMember(req);
    }

    @Test
    @Transactional
    public void 팔로우테스트() {
        Member from = memberService.getMember("testid").get();
        memberService.follow(from, "id1");

        Member to = memberService.getMember("id1").get();

        assertEquals(1, from.getFollowings().size());
        assertEquals(1, to.getFollowers().size());

        assertEquals(0, to.getFollowings().size());
        assertEquals(0, from.getFollowers().size());

        MemberFollow follow = memberFollowRepository.findByMemberAndFollow(from, to).get(0);
        assertEquals(1, memberFollowRepository.findByMemberAndFollow(from, to).size());
        
        assertEquals("testid", follow.getMember().getUsername());
        assertEquals("id1", follow.getFollow().getUsername());


        for(MemberFollow f : memberFollowRepository.findByMemberAndFollow(from, to)){
            log.info("member : {}, follow : {}", f.getMember().getUsername(), f.getFollow().getUsername());
        }
    }
}
