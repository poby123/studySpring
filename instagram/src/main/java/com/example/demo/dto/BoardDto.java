package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.example.demo.dto.BoardImageDto.BoardImageViewDto;
import com.example.demo.dto.BoardLikeDto.BoardLikeForBoardDto;
import com.example.demo.dto.MemberDto.MemberBoardViewDto;
import com.example.demo.entity.Board;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BoardDto {

    @Data
    @NoArgsConstructor
    public static class BoardSaveDto {

        @NotBlank(message = "게시물의 제목은 필수항목입니다.")
        private String title;
        
        private String content;
        private List<MultipartFile> files;
    }

    @Data
    @NoArgsConstructor
    public static class BoardViewDto {

        private Long id;
        private String title;
        private String content;
        private MemberBoardViewDto writer;
        private List<BoardImageViewDto> images;
        private List<BoardLikeForBoardDto> likes;

        public static BoardViewDto of(Board board) {
            BoardViewDto ret = new BoardViewDto();
            ret.setId(board.getId());
            ret.setTitle(board.getTitle());
            ret.setContent(board.getContent());
            ret.setWriter(MemberBoardViewDto.of(board.getWriter()));

            List<BoardImageViewDto> images = board.getImages().stream().map(BoardImageViewDto::of)
                    .collect(Collectors.toUnmodifiableList());
            ret.setImages(images);

            List<BoardLikeForBoardDto> likes = board.getLikes().stream().map(BoardLikeForBoardDto::of).collect(Collectors.toUnmodifiableList());
            ret.setLikes(likes);


            return ret;
        }
    }
}
