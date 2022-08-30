package com.example.demo.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.dto.CommentSaveDto;
import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.repositoy.CommentRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;
import com.example.demo.global.exception.types.BoardNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Page<CommentViewDto> getCommentViewDtoPage(Long boardId, int page){
        page = (page == 0 ? 0 : page - 1);
		final Pageable pageable = PageRequest.of(page, 10);

        return commentRepository.findBoardCommentViewDtoPage(boardId, pageable);
    }

    public List<CommentViewDto> getCommentViewDtoList(List<Long> boardIds){
        return commentRepository.findBoardCommentViewDtoList(boardIds);
    }

    @Transactional
    public Comment save(Member member, CommentSaveDto commentSaveDto) {
        Board board = boardRepository.findById(commentSaveDto.getBoardId()).orElseThrow(BoardNotFoundException::new);
        Comment comment = new Comment(member, commentSaveDto.getContent());
        board.addComment(comment);
        return comment;
    }


    @Transactional
    public void remove(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }
}
