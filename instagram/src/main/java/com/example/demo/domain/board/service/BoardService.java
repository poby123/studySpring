package com.example.demo.domain.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.board.dto.BoardDto.BoardSaveDto;
import com.example.demo.domain.board.dto.BoardDto.BoardViewDto;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.BoardImage;
import com.example.demo.domain.board.entity.BoardLike;
import com.example.demo.domain.board.repositoy.BoardLikeRepository;
import com.example.demo.domain.board.repositoy.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.global.exception.types.BoardNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final S3Service s3Service;

    @Transactional
    public Long save(Member member, BoardSaveDto dto) {
        Board board = Board.createBoard(member, dto.getTitle(), dto.getContent(), new ArrayList<>());

        List<BoardImage> images = s3Service.upload(dto.getFiles()).stream().map(BoardImage::new).collect(Collectors.toList());
        for(BoardImage image : images){
            board.addImage(image);
        }

        board = boardRepository.save(board);
        return board.getId();
    }


    @Transactional
    public void remove(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        board.getImages().forEach(image -> s3Service.remove(image.getUrl()));

        // boardImage가 orphanRemoval = true, cascade = CascadeType.ALL 이렇게 되어있어서 따로 처리해줄
        // 필요가 없다.
        boardRepository.delete(board);
    }


    public Board findOne(Long id) {
        return boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
    }


    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    
    @Transactional
    public void likeBoard(Member member, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        List<BoardLike> like = boardLikeRepository.findByMemberAndBoard(member, board);

        if(like.isEmpty()){
            BoardLike.doLike(member, board);
        }
        else{
            BoardLike.undoLike(like.get(0));
        }
    }
}
