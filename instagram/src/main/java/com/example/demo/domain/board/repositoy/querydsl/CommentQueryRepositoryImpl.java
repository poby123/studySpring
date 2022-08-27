package com.example.demo.domain.board.repositoy.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.dto.QCommentViewDto;
import com.example.demo.domain.board.entity.QComment;
import com.example.demo.domain.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory query;
    private final QComment qComment = QComment.comment;

    public Page<CommentViewDto> findBoardCommentViewDtoPage(Long boardId, Pageable pageable) {
        List<CommentViewDto> dtos = query
                .select(new QCommentViewDto(qComment.writer, qComment.content))
                .from(qComment)
                .where(qComment.board.id.eq(boardId))
                .innerJoin(qComment.writer, QMember.member)
                .orderBy(qComment.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = query.selectFrom(qComment).where(qComment.board.id.eq(boardId)).fetch().size();
        return new PageImpl<>(dtos, pageable, total);
    }
}
