package com.example.demo.domain.board.repositoy.querydsl;

import com.example.demo.domain.board.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardQueryRepositoryImpl implements BoardQueryRepository{

    private final JPAQueryFactory query;
    private final QBoard board = QBoard.board;

    // public Optional<Board> findById(Long id) {
    //     Board result = query.selectFrom(board).where(board.id.eq(id)).fetchOne();
    //     return Optional.ofNullable(result);
    // }

    // public List<Board> findAll() {
    //     return query.selectFrom(board).fetch();
    // }
}
