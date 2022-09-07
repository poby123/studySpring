package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.BoardLikeListDto;
import com.querydsl.core.Tuple;

@Repository
public interface BoardLikeQueryRepository {
    public List<Tuple> findBoardLikeDto(List<Long> boardIds, String username);
    public List<BoardLikeListDto> findBoardLikeListDto(List<Long> boardId);
}
