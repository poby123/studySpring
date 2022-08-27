package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class QueryDslTest {

    @Autowired 
    BoardRepository boardRepository;

    @Autowired 
    MemberRepository memberRepository;

    @Test
    public void selectFromBoardTest() {
        // given
        Member m = new Member("username", "name", "password", "email");
        memberRepository.save(m);

        // when
        boardRepository.save(Board.createBoard(m, "title", "content", new ArrayList<>()));
        boardRepository.save(Board.createBoard(m, "title2", "content", new ArrayList<>()));

        // then
        List<Board> result = boardRepository.findAll();
        // assertEquals(2, result.size());
        // assertEquals("content", result.get(0).getContent());
        // assertEquals("username", result.get(0).getMember().getUsername());

        for(Board b : result){
            log.info(b.getContent());
        }
    }
}
