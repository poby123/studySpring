package com.example.demo.service.member;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.member.repositoy.MemberFollowRepository;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.example.demo.domain.member.service.UserDetailsServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
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

    // @Test(expected = UsernameDuplicateException.class)
    @Ignore
    public void usernameDuplicatedExceptionTest() {
       
    }
    

    @Ignore
    public void getMemberWithDto(){
        
    }
}
