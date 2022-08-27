package com.example.demo.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.repositoy.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public Page<CommentViewDto> getCommentViewDtoPage(Long boardId, int page){
        page = (page == 0 ? 0 : page - 1);
		final Pageable pageable = PageRequest.of(page, 10);

        return commentRepository.findBoardCommentViewDtoPage(boardId, pageable);
    }

    public List<CommentViewDto> getCommentViewDtoList(List<Long> boardIds){
        return commentRepository.findBoardCommentViewDtoList(boardIds);
    }
}
