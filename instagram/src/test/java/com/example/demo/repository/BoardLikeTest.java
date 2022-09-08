package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.QBoardLike;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class BoardLikeTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

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
    public void registerComment() {
        // given
        init();
        Long boardId = board.getId();

        // when
        boardService.likeBoard(boardId);

        // then
        List<Tuple> result = findBoardLikeDto(List.of(boardId), "poby123");

        for(Tuple t : result){
            log.info("result : " + t.get(0, Long.class) + " " + t.get(1, String.class));
        }
    }

    public List<Tuple> findBoardLikeDto(List<Long> boardIds, String username) {
        return query
                .select(qBoardLike.board.id, qBoardLike.member.username)
                .from(qBoardLike)
                .leftJoin(qBoardLike.member)
                .where(qBoardLike.board.id.in(boardIds))
                .fetch();
    }
}
