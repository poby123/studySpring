package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import com.example.demo.domain.board.dto.BoardLikeDto;
import com.example.demo.domain.board.dto.BoardLikeListDto;
import com.example.demo.domain.board.dto.QBoardLikeDto;
import com.example.demo.domain.board.dto.QBoardLikeListDto;
import com.example.demo.domain.board.entity.QBoardLike;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLikeQueryRepositoryImpl implements BoardLikeQueryRepository{
    private final JPAQueryFactory query;
    private final QBoardLike qBoardLike = QBoardLike.boardLike;

    public List<BoardLikeDto> findBoardLikeDto(List<Long> boardIds){
        // query.select(new QBoardLikeDto(qBoardLike.board.id, numberOfLikes, isCurrentMemberLike))
        // .from(qBoardLike)
        query.select(qBoardLike)
        .where(qBoardLike.board.id.in(boardIds))
        .fetch()
        .size();

        return null;

        /*
         * SELECT COUNT(*) FROM board_like
         * WHERE board_id in (?, ?, ?, ?)
         */
    }

    public List<BoardLikeListDto> findBoardLikeListDto(List<Long> boardIds){
        List<BoardLikeListDto> dtos = query
        .select(new QBoardLikeListDto(qBoardLike.board.id, qBoardLike.member.username, qBoardLike.member.image))
        .from(qBoardLike)
        .where(qBoardLike.board.id.in(boardIds))
        .fetch();

        return dtos;
    }
}
