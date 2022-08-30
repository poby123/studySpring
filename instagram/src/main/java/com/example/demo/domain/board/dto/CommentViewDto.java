package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.member.dto.MemberBoardViewDto;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentViewDto {
    private Long commentId;
    private String content;
    private Long boardId;
    private MemberBoardViewDto member;

    @QueryProjection
    public CommentViewDto(Long commentId, String content, String username, String userProfileImage, Long boardId) {
        this.boardId = boardId;
        this.commentId = commentId;
        this.member = new MemberBoardViewDto(username, userProfileImage);
        this.content = content;
    }

    public static CommentViewDto of(Comment entity) {
        return new CommentViewDto(
            entity.getId(), entity.getContent(), 
            entity.getWriter().getUsername(),
            entity.getWriter().getImage(), 
            entity.getBoard().getId()
        );
    }
}
