package com.example.demo.service.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.api.service.BoardApiService;
import com.example.demo.domain.board.dto.BoardImageViewDto;
import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class BoardApiServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;
    
    @Autowired
    BoardApiService boardApiService;

    Board board;
    Member member;
    Comment comment;

    private Member createMember() {
        return new Member("username", "name", "password", "email");
    }

    private Board createBoard(Member member) {
        return Board.createBoard(member, "title", "content", new ArrayList<>());
    }

    private Comment createComment(Member member, String content){
        return new Comment(member, content);
    }

    private void init() {
        member = createMember();
        memberRepository.save(member);

        board = createBoard(member);
        boardRepository.save(board);

        comment = createComment(member, "comment content");
        board.addComment(comment);
    }

    @Test
    public void getBoardViewDtoTestA(){
        // given
        init();

        // when
        BoardViewDto result = boardApiService.getBoardViewDto(board.getId());
        
        // then
        assertEquals("content", result.getContent());
        assertEquals(1, result.getComments().size());
        assertEquals("username", result.getComments().get(0).getMember().getUsername());
        assertEquals("comment content", result.getComments().get(0).getContent());

        log.info("Test A");
    }

    @Test
    public void getBoardViewDtoTestB(){
        // given
        log.info("Test B");
        init();

        // when
        List<BoardViewDto> result = boardApiService.getBoardViewDtoPage(10, 0).getContent();
        // final Pageable pageable = PageRequest.of(page, size);
        // Page<BoardViewDto> postDtoPage = boardRepository.findBoardViewDtoPage(pageable);

        // List<BoardViewDto> dtos = postDtoPage.getContent();
        // List<Long> boardIds = dtos.stream().map(d -> d.getId()).collect(Collectors.toUnmodifiableList());
        
        // List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(boardIds);
        // Map<Long, List<BoardImageViewDto>> boardImageMap = images.stream().collect(Collectors.groupingBy(BoardImageViewDto::getBoardId));
        // dtos.forEach(dto -> dto.setImages(boardImageMap.get(dto.getId())));
        
        // then
        for(BoardViewDto dto : result){
            log.info(dto.getContent());
        }
        
    }

}
