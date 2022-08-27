package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.BoardImageViewDto;
import com.example.demo.domain.board.dto.QBoardImageViewDto;
import com.example.demo.domain.board.entity.QBoardImage;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardImageQueryRepository {

    private final JPAQueryFactory query;
    private final QBoardImage qBoardImage = QBoardImage.boardImage;

    public List<BoardImageViewDto> findAllBoardImageViewDto(List<Long> boardIds){
        return query.select(new QBoardImageViewDto(
            qBoardImage.url
        ))
        .from(qBoardImage)
        .where(qBoardImage.board.id.in(boardIds))
        .fetch();
    }
}
