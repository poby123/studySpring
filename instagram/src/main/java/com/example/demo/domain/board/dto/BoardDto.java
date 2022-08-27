package com.example.demo.domain.board.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

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
}
