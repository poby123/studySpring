package com.example.demo.repository;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.BoardLike;
import com.example.demo.domain.board.entity.QBoardLike;
import com.example.demo.domain.board.repositoy.BoardLikeRepository;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BoardLikeTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardLikeRepository boardLikeRepository;

    @Autowired
    JPAQueryFactory query;

    Board board;
    Member member;
    QBoardLike qBoardLike = QBoardLike.boardLike;

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
    public void duplicateExceptionTest() {
        // given
        init();

        // when
        Long id = boardLikeRepository.save(new BoardLike(member, board)).getId();
        boardLikeRepository.findById(id);

        
        // boardLikeRepository.save(new BoardLike(member, board));
    }
}
