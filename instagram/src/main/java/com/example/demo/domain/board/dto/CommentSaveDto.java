package com.example.demo.domain.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentSaveDto {

    @NotNull(message = "게시글 아이디는 필수 항목입니다.")
    Long boardId;

    @NotBlank(message = "댓글은 빈 내용일 수 없습니다")
    String content;
}
