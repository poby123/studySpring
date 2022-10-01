package com.example.demo.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.LogExecutionTime;
import com.example.demo.api.service.BoardApiService;
import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.global.result.ResultCode;
import com.example.demo.global.result.ResultResponse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "게시물 API")
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardApiController {

    private final BoardService boardService;
    private final BoardApiService boardApiService;

    @Operation(summary = "Get board list (paging)", description = "페이징된 게시글을 가져온다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @LogExecutionTime(targetName = "/api/boards")
    @GetMapping
    public ResponseEntity<ResultResponse> getList(@AuthenticationPrincipal Member member,@RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "0", required = false) int page) {
        log.info("member : {}", member.getUsername());
        final Page<BoardViewDto> result = boardApiService.getBoardViewDtoPage(size, page);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.FIND_POST_PAGE_SUCCESS, result));
    }

    @Operation(summary = "Get board", description = "게시글을 하나 가져온다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{boardId}")
    public ResponseEntity<ResultResponse> getBoard(@PathVariable("boardId") long boardId) {
        final BoardViewDto result = boardApiService.getBoardViewDto(boardId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.FIND_POST_SUCCESS, result));
    }

    @Operation(summary = "Delete board", description = "게시글을 삭제한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResultResponse> deleteBoard(@PathVariable("boardId") long boardId) {
        boardService.remove(boardId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_POST_SUCCESS));
    }

    @Operation(summary = "Do like or dislike board", description = "좋아요 혹은 좋아요 해제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/like/{boardId}")
    public ResponseEntity<ResultResponse> toggleBoardLike(@PathVariable("boardId") long boardId) {
        boolean isBoardLiked = boardService.likeBoard(boardId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.LIKE_POST_SUCCESS, isBoardLiked));
    }

    /*
     * @Transactional
     * public void remove(Long id) {
     * Board board =
     * boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
     * 
     * board.getImages().forEach(image -> s3Service.remove(image.getUrl()));
     * 
     * // boardImage가 orphanRemoval = true, cascade = CascadeType.ALL 이렇게 되어있어서 따로
     * 처리해줄
     * // 필요가 없다.
     * boardRepository.delete(board);
     * }
     * 
     * 
     * public BoardViewDto findOne(Long id) {
     * Board board =
     * boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
     * return BoardViewDto.of(board);
     * }
     * 
     * 
     * public List<BoardViewDto> findAll() {
     * List<Board> boards = boardRepository.findAll();
     * List<BoardViewDto> ret =
     * boards.stream().map(BoardViewDto::of).collect(Collectors.toUnmodifiableList()
     * );
     * 
     * return ret;
     * }
     * 
     * 
     * @Transactional
     * public void likeBoard(Member member, Long boardId) {
     * Board board =
     * boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
     * List<BoardLike> like = boardLikeRepository.findByMemberAndBoard(member,
     * board);
     * 
     * if(like.isEmpty()){
     * BoardLike.doLike(member, board);
     * }
     * else{
     * BoardLike.undoLike(like.get(0));
     * }
     * }
     */
}
