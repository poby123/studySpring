package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import com.example.demo.domain.board.dto.BoardLikeDto;
import com.example.demo.domain.board.dto.QBoardLikeDto;
import com.example.demo.domain.board.entity.QBoardLike;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLikeQueryRepositoryImpl implements BoardLikeQueryRepository{
    private final JPAQueryFactory query;
    private final QBoardLike qBoardLike = QBoardLike.boardLike;

    public List<BoardLikeDto> findBoardLikes(Long boardId){
        List<BoardLikeDto> dtos = query
        .select(new QBoardLikeDto(qBoardLike.board.id, qBoardLike.member))
        .from(qBoardLike)
        .where(qBoardLike.board.id.eq(boardId))
        .fetch();

        return dtos;
    }
}
