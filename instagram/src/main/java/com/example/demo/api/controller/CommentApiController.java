package com.example.demo.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.board.dto.CommentSaveDto;
import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.board.service.CommentService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.UserDetailsServiceImpl;
import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentApiController {

    private final CommentService commentService;
    private final UserDetailsServiceImpl userDetailsService;

    @Operation(summary = "Save comment", description = "댓글을 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping
    public CommentViewDto save(@RequestBody @Valid CommentSaveDto commentSaveDto) {
        Member member = userDetailsService.getMember("poby123")
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        Comment comment = commentService.save(member, commentSaveDto);
        return CommentViewDto.of(comment);
    }


    @Operation(summary = "Get comments", description = "게시물의 댓글을 가져온다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{boardId}")
    public List<CommentViewDto> getCommentViewDtoList(@PathVariable("boardId") Long boardId) {
        return commentService.getCommentViewDtoList(List.of(boardId));
    }

    @Operation(summary = "Delete a comment", description = "게시물의 댓글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.remove(commentId);
    }

}