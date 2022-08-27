package com.example.demo.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.dto.BoardImageViewDto;
import com.example.demo.domain.board.dto.BoardLikeDto;
import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.repositoy.BoardLikeRepository;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.repositoy.querydsl.BoardImageQueryRepository;
import com.example.demo.domain.board.service.CommentService;
import com.example.demo.global.exception.types.BoardNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardApiService {
    
    private final BoardRepository boardRepository;
    private final BoardImageQueryRepository boardImageRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final CommentService commentService;

    public BoardViewDto getBoardViewDto(Long boardId){
        BoardViewDto dto = boardRepository.findBoardViewDto(boardId).orElseThrow(BoardNotFoundException::new);
        setBoardImages(dto);
        setBoardLike(dto);
        setBoardComments(dto);

        return dto;
    }

    public Page<BoardViewDto> getBoardViewDtoPage(int size, int page){
        final Pageable pageable = PageRequest.of(page, size);
        Page<BoardViewDto> postDtoPage = boardRepository.findBoardViewDtoPage(pageable);


        return postDtoPage;
    }

    private void setBoardImages(BoardViewDto dto){
        List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(List.of(dto.getId()));
        dto.setImages(images); 
    }

    private void setBoardLike(BoardViewDto dto){
        List<BoardLikeDto> likes = boardLikeRepository.findBoardLikes(dto.getId());
        dto.setLikes(likes);
    }

    private void setBoardComments(BoardViewDto dto) {
        List<CommentViewDto> comments = commentService.getCommentViewDtoPage(dto.getId(), 0).getContent();
        dto.setComments(comments);
    }
}
