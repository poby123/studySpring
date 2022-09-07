package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import com.example.demo.domain.board.dto.BoardLikeListDto;
import com.example.demo.domain.board.dto.QBoardLikeListDto;
import com.example.demo.domain.board.entity.QBoardLike;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLikeQueryRepositoryImpl implements BoardLikeQueryRepository {
    private final JPAQueryFactory query;
    private final QBoardLike qBoardLike = QBoardLike.boardLike;

    public List<Tuple> findBoardLikeDto(List<Long> boardIds, String username) {
        return query
                .select(qBoardLike.board.id, qBoardLike.member.username)
                .from(qBoardLike)
                .where(qBoardLike.board.id.in(boardIds))
                .fetch();
    }

    public List<BoardLikeListDto> findBoardLikeListDto(List<Long> boardIds) {
        List<BoardLikeListDto> dtos = query
                .select(new QBoardLikeListDto(qBoardLike.board.id, qBoardLike.member.username, qBoardLike.member.image))
                .from(qBoardLike)
                .where(qBoardLike.board.id.in(boardIds))
                .fetch();

        return dtos;
    }
}
