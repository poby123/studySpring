package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.BoardImage;
import com.example.demo.domain.board.service.S3Service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BoardImageDto {
    
    @Data
    @NoArgsConstructor
    public static class BoardImageViewDto{
        private String filePath;
        private String fullFilePath;

        public static BoardImageViewDto of(BoardImage image){            
            BoardImageViewDto ret = new BoardImageViewDto();
            ret.setFilePath(image.getUrl());
            ret.setFullFilePath(S3Service.CLOUD_FRONT_DOMAIN_NAME + image.getUrl());

            return ret;
        }
    }
}
