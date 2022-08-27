package com.example.demo.domain.board.dto;

import java.util.List;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.member.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardViewDto {
    private Long id;
    private String title;
    private String content;
    private MemberBoardViewDto writer;
    private List<CommentViewDto> comments;
    private List<BoardImageViewDto> images;
    private List<BoardLikeDto> likes;

    @QueryProjection
    public BoardViewDto(Long id, String title, String content, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = new MemberBoardViewDto(member);
    }

    public static BoardViewDto of(Board board) {
        BoardViewDto ret = new BoardViewDto();
        ret.setId(board.getId());
        ret.setTitle(board.getTitle());
        ret.setContent(board.getContent());
        ret.setWriter(MemberBoardViewDto.of(board.getMember()));

        return ret;
    }
}
