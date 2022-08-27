package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BoardCommentTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    Board board;
    Member member;

    private Member createMember() {
        return new Member("username", "name", "password", "email");
    }

    private Board createBoard(Member member) {
        return Board.createBoard(member, "title", "content", new ArrayList<>());
    }

    public void init() {
        member = createMember();
        memberRepository.save(member);

        board = createBoard(member);
        boardRepository.save(board);
    }

    @Test
    public void registerComment() {
        // given
        init();
        Long boardId = board.getId();

        // when
        board.addComment(new Comment(member, "this is comment content1"));
        board.addComment(new Comment(member, "this is comment content2"));

        // then
        board = boardRepository.findById(boardId).get();
        assertEquals(2, board.getComments().size());
        assertEquals("this is comment content1", board.getComments().get(0).getContent());
        assertEquals("this is comment content2", board.getComments().get(1).getContent());
        assertEquals("username", board.getComments().get(0).getWriter().getUsername());
    }

}
