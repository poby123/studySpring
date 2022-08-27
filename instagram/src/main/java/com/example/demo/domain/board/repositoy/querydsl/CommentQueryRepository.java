package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.CommentViewDto;

@Repository
public interface CommentQueryRepository {
    public Page<CommentViewDto> findBoardCommentViewDtoPage(Long boardId, Pageable pageable);
    public List<CommentViewDto> findBoardCommentViewDtoList(List<Long> boardIds);
}
