package com.example.demo.service.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.hibernate.annotations.BatchSize;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.member.dto.MemberDto.MemberProfileViewDto;
import com.example.demo.domain.member.dto.MemberDto.SignupRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberFollowRepository;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.example.demo.domain.member.service.UserDetailsServiceImpl;
import com.example.demo.global.exception.types.UsernameDuplicateException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    @Autowired
    UserDetailsServiceImpl memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberFollowRepository memberFollowRepository;

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
    @BatchSize(size = 100)
    // @Ignore
    public void getMemberWithDto(){
        //given
        final String member1Id = "test-member-id1";
        final String member2Id = "test-member-id2";

        Member member1 = new Member(member1Id, "member1","password1", "member1@naver.com");
        Member member2 = new Member(member2Id, "member2","password2", "member2@naver.com");
        
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);
        
        //when
        Board b = Board.createBoard(member1, "member1-title1", "member1-content1", new ArrayList<>());
        Board.createBoard(member1, "member1-title2", "member1-content2", new ArrayList<>());
        Board.createBoard(member1, "member1-title3", "member1-content3", new ArrayList<>());

        memberService.follow(member2, member1Id);
        boardService.likeBoard(member2, b.getId());

        
        //then 
        MemberProfileViewDto dto = memberService.getMemeberToProfileViewDto(member1Id);
        assertEquals(member1Id, dto.getUsername());

        assertEquals(3, dto.getBoards().size());
        assertEquals(1, dto.getBoards().get(0).getLikes().size());
        assertEquals(0, dto.getBoards().get(1).getLikes().size());
        assertEquals(0, dto.getBoards().get(2).getLikes().size());

        assertEquals(1, dto.getFollowers().size());
        assertEquals(member2Id, dto.getFollowers().get(0).getUsername());
        // assertEquals(0, dto.getFollowings().size());
    }
}
