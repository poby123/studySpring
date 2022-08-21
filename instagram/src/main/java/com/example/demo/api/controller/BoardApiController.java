package com.example.demo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.board.dto.BoardDto.BoardViewDto;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    
    private final BoardService boardService;

    @Operation(summary = "gallery version1", description = "not optimized. get boards")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/v1/gallery") 
    public List<BoardViewDto> getList(){
        List<Board> boards =  boardService.findAll();
        List<BoardViewDto> ret = boards.stream().map(BoardViewDto::of).collect(Collectors.toUnmodifiableList());

        return ret;
    }

    /*
    @Transactional
    public void remove(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        board.getImages().forEach(image -> s3Service.remove(image.getUrl()));

        // boardImage가 orphanRemoval = true, cascade = CascadeType.ALL 이렇게 되어있어서 따로 처리해줄
        // 필요가 없다.
        boardRepository.delete(board);
    }


    public BoardViewDto findOne(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardViewDto.of(board);
    }


    public List<BoardViewDto> findAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardViewDto> ret = boards.stream().map(BoardViewDto::of).collect(Collectors.toUnmodifiableList());

        return ret;
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
    */
}
