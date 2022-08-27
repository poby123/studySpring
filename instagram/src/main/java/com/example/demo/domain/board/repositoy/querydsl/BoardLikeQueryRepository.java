package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.BoardLikeDto;

@Repository
public interface BoardLikeQueryRepository {
    public List<BoardLikeDto> findBoardLikes(Long boardId);
}
