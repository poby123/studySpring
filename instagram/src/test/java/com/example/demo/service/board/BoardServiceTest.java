package com.example.demo.service.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.BoardImage;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    MemberRepository memberRepository;

    public Board createBoard(String title, String content, String writer) {
        String[] images_url = { "img1", "img2", "img3", "img4" };
        List<BoardImage> images = new ArrayList<>();

        for (String url : images_url) {
            BoardImage target = new BoardImage(url);
            images.add(target);
        }

        Member member = memberRepository.findByUsername(writer).get();
        return Board.createBoard(member, title, content, images);

    }

    @Test
    @Ignore
    public void 이미지저장테스트() {
        final String title = "서울 체크인 디자인!";
        final String content = "티빙의 오리지널 시리즈 서울 체크인의 오프닝과 로고를 제작하였습니다";
        final String writer = "poby123";

        Board board = createBoard(title, content, writer);
        board = boardRepository.save(board);
        board = boardRepository.findById(board.getId()).get();

        assertEquals(content, board.getContent());
        assertEquals(writer, board.getMember().getUsername());
        assertEquals("img1", board.getImages().get(0).getUrl());
        assertEquals("img2", board.getImages().get(1).getUrl());
        assertEquals("img3", board.getImages().get(2).getUrl());
        assertEquals("img4", board.getImages().get(3).getUrl());
    }

    @Test
    @Transactional
    public void 좋아요테스트() {
        // given
        final String title = "서울 체크인 디자인!";
        final String content = "티빙의 오리지널 시리즈 서울 체크인의 오프닝과 로고를 제작하였습니다";
        final String writer = "poby123";

        Board board = createBoard(title, content, writer);
        board = boardRepository.save(board);
        Long boardId = board.getId();

        // when1
        boardService.likeBoard(boardId);
        
        // then1
        board = boardRepository.findById(boardId).get();
        assertEquals(1, board.getLikes().size());
        assertEquals(writer, board.getLikes().get(0).getMember().getUsername());
        
        // when2
        boardService.likeBoard(boardId);

        // then2
        board = boardRepository.findById(boardId).get();
    }

    @Test
    @Ignore
    public void 가져오기테스트() {
        
    }

}
