package com.example.demo.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.dto.BoardImageViewDto;
import com.example.demo.domain.board.dto.BoardLikeDto;
import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.dto.CommentViewDto;
import com.example.demo.domain.board.repositoy.BoardLikeRepository;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.board.repositoy.querydsl.BoardImageQueryRepository;
import com.example.demo.domain.board.service.CommentService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.UserDetailsServiceImpl;
import com.example.demo.global.exception.types.BoardNotFoundException;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardApiService {
    
    private final BoardRepository boardRepository;
    private final BoardImageQueryRepository boardImageRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final CommentService commentService;
    private final UserDetailsServiceImpl memberService;

    public BoardViewDto getBoardViewDto(Long boardId){
        BoardViewDto dto = boardRepository.findBoardViewDto(boardId).orElseThrow(BoardNotFoundException::new);

        Member signedUser = memberService.getMember("poby123").get();

        setBoardImages(dto);
        setBoardLikes(dto, signedUser.getId());
        setBoardComment(dto);

        return dto;
    }

    public Page<BoardViewDto> getBoardViewDtoPage(int size, int page){
        final Pageable pageable = PageRequest.of(page, size);
        Page<BoardViewDto> postDtoPage = boardRepository.findBoardViewDtoPage(pageable);

        List<BoardViewDto> dtos = postDtoPage.getContent();
        List<Long> boardIds = dtos.stream().map(d -> d.getId()).collect(Collectors.toUnmodifiableList());

        Member signedUser = memberService.getMember("poby123").get();

        setBoardImages(dtos, boardIds);
        setBoardLikes(dtos, boardIds, signedUser.getId());
        setBoardComments(dtos, boardIds);

        return postDtoPage;
    }


    private void setBoardImages(BoardViewDto dto){
        List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(List.of(dto.getId()));
        dto.setImages(images);
    }

    private void setBoardImages(List<BoardViewDto> dtos, List<Long> boardIds){
        List<BoardImageViewDto> images = boardImageRepository.findAllBoardImageViewDto(boardIds);
        Map<Long, List<BoardImageViewDto>> boardImageMap = images.stream().collect(Collectors.groupingBy(BoardImageViewDto::getBoardId));
        dtos.forEach(dto -> dto.setImages(boardImageMap.getOrDefault(dto.getId(), new ArrayList<>())));
    }

    private void setBoardLikes(BoardViewDto dto, Long userId){
        setBoardLikes(List.of(dto), List.of(dto.getId()), userId);
    }

    private void setBoardLikes(List<BoardViewDto> dtos, List<Long> boardIds, Long memberId){
        List<Tuple> likes = boardLikeRepository.findBoardLikeDto(boardIds);
        dtos.forEach(dto -> dto.setLike(convertToMemberLikeDto(likes, memberId).getOrDefault(dto.getId(), new BoardLikeDto(dto.getId(), 0, false))));
    }

    private void setBoardComment(BoardViewDto dto) {
        List<CommentViewDto> comments = commentService.getCommentViewDtoPage(dto.getId(), 0).getContent();
        dto.setComments(comments);
    }

    private void setBoardComments(List<BoardViewDto> dtos, List<Long> boardIds) {
        List<CommentViewDto> comments = commentService.getCommentViewDtoList(boardIds);
        Map<Long, List<CommentViewDto>> commentMap = comments.stream().collect(Collectors.groupingBy(CommentViewDto::getBoardId));
        dtos.forEach(dto -> dto.setComments(commentMap.getOrDefault(dto.getId(), new ArrayList<>())));
    }


    /*
     * Utility functions
     */
    private Map<Long, BoardLikeDto> convertToMemberLikeDto(List<Tuple> likes, Long memberId){
        Map<Long, List<Tuple>> boardLikeMap = likes.stream().collect(Collectors.groupingBy(t -> t.get(0, Long.class)));
        Map<Long, Boolean> isMemberLike = new HashMap<>();
        Map<Long, BoardLikeDto> ret = new HashMap<>(); 

        boardLikeMap.forEach((Long boardId, List<Tuple> content) -> {
            List<Long> likeUsernames = content.stream().map(c -> c.get(1, Long.class)).collect(Collectors.toUnmodifiableList());
            isMemberLike.put(boardId, likeUsernames.contains(memberId));
        });

        isMemberLike.forEach((Long boardId, Boolean isCurrentMemberLike) -> {
            int size = boardLikeMap.get(boardId).size();
            ret.put(boardId, new BoardLikeDto(boardId, size, isCurrentMemberLike));
        });

        return ret;
    }
}
