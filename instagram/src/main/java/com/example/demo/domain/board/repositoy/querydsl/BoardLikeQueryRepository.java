package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.BoardLikeDto;
import com.example.demo.domain.board.dto.BoardLikeListDto;

@Repository
public interface BoardLikeQueryRepository {
    public List<BoardLikeDto> findBoardLikeDto(List<Long> boardIds);
    public List<BoardLikeListDto> findBoardLikeListDto(List<Long> boardId);
}
