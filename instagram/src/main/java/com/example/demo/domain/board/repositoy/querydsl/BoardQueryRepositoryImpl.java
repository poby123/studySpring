package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.dto.QBoardViewDto;
import com.example.demo.domain.board.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardQueryRepositoryImpl implements BoardQueryRepository {

    private final JPAQueryFactory query;
    private final QBoard qBoard = QBoard.board;

    public Page<BoardViewDto> findBoardViewDtoPage(Pageable pageable){
        final List<BoardViewDto> dtos = query.select(
            new QBoardViewDto(
                qBoard.id, qBoard.title, qBoard.content, qBoard.member.username, qBoard.member.image
            ))
            .from(qBoard)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(qBoard.id.desc())
            .fetch();

        long total = query.selectFrom(qBoard).fetch().size();
        return new PageImpl<>(dtos, pageable, total);
    }

    public Optional<BoardViewDto> findBoardViewDto(Long boardId) {
        return Optional.ofNullable(
                query.select(new QBoardViewDto(
                        qBoard.id, qBoard.title, qBoard.content, qBoard.member.username, qBoard.member.image))
                        .from(qBoard)
                        .where(qBoard.id.eq(boardId))
                        .fetchOne());
    }

    // public Optional<Board> findById(Long id) {
    // Board result = query.selectFrom(board).where(board.id.eq(id)).fetchOne();
    // return Optional.ofNullable(result);
    // }

    // public List<Board> findAll() {
    // return query.selectFrom(board).fetch();
    // }
}
