package com.example.demo.repository;

import static com.example.demo.entity.QMember.member;
import static com.example.demo.entity.QBoard.board;
import static com.example.demo.entity.QBoardImage.boardImage;

import java.util.List;

import com.example.demo.dto.BoardDto.BoardViewDto;
import com.example.demo.dto.BoardImageDto.BoardImageViewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements CustomBoardRepository{

    private final JPAQueryFactory query;

    // private Long id;
    // private String title;
    // private String content;
    // private MemberBoardViewDto writer;
    // private List<BoardImageViewDto> images;
    // private List<BoardLikeForBoardDto> likes;

    @Override
    public BoardViewDto findBoardViewDtoById(Long id) {
        BoardViewDto ret = query.select(Projections.bean(BoardViewDto.class, board.id, board.title, board.content, board.writer))
                                .where(board.id.eq(id))
                                .from(board)
                                .leftJoin(member)
                                .fetchOne();

        
        return ret;
    }

    public List<BoardImageViewDto> findBoardImageViewDtoById(Long id) {
        return query.select(Projections.bean(BoardImageViewDto.class, board.id, board.title, board.content))
                                .where(boardImage.board.id.eq(id))
                                .fetch();
    }


    
}
