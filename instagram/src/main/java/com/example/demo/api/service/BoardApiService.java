package com.example.demo.api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        setBoardComment(dto);

        return dto;
    }

    public Page<BoardViewDto> getBoardViewDtoPage(int size, int page){
        final Pageable pageable = PageRequest.of(page, size);
        Page<BoardViewDto> postDtoPage = boardRepository.findBoardViewDtoPage(pageable);

        List<BoardViewDto> dtos = postDtoPage.getContent();
        List<Long> boardIds = dtos.stream().map(d -> d.getId()).collect(Collectors.toUnmodifiableList());

        setBoardImages(dtos, boardIds);
        setBoardLikes(dtos, boardIds);
        setBoardComments(dtos, boardIds);

        return postDtoPage;
    }

    private void setBoardImages(BoardViewDto dto){
        List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(List.of(dto.getId()));
        dto.setImages(images);
    }

    private void setBoardImages(List<BoardViewDto> dtos, List<Long> boardIds){
        List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(boardIds);
        Map<Long, List<BoardImageViewDto>> boardImageMap = images.stream().collect(Collectors.groupingBy(BoardImageViewDto::getBoardId));
        dtos.forEach(dto -> dto.setImages(boardImageMap.get(dto.getId())));
    }

    private void setBoardLike(BoardViewDto dto){
        List<BoardLikeDto> likes = boardLikeRepository.findBoardLikes(List.of(dto.getId()));
        dto.setLikes(likes);
    }

    private void setBoardLikes(List<BoardViewDto> dtos, List<Long> boardIds){
        List<BoardLikeDto> likes = boardLikeRepository.findBoardLikes(boardIds);
        Map<Long, List<BoardLikeDto>> boardLikeMap = likes.stream().collect(Collectors.groupingBy(BoardLikeDto::getBoardId));
        dtos.forEach(dto -> dto.setLikes(boardLikeMap.get(dto.getId())));
    }

    private void setBoardComment(BoardViewDto dto) {
        List<CommentViewDto> comments = commentService.getCommentViewDtoPage(dto.getId(), 0).getContent();
        dto.setComments(comments);
    }

    private void setBoardComments(List<BoardViewDto> dtos, List<Long> boardIds) {
        List<CommentViewDto> comments = commentService.getCommentViewDtoList(boardIds);
        Map<Long, List<CommentViewDto>> commentMap = comments.stream().collect(Collectors.groupingBy(CommentViewDto::getBoardId));
        dtos.forEach(dto -> dto.setComments(commentMap.get(dto.getId())));
    }
}
