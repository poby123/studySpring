package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.BoardImage;
import com.example.demo.domain.board.service.S3Service;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardImageViewDto {
    
    private Long boardId;
    private String filePath;
    private String fullFilePath;

    @QueryProjection
    public BoardImageViewDto(Long boardId, String url) {
        this.boardId = boardId;
        this.filePath = url;
        this.fullFilePath = S3Service.CLOUD_FRONT_DOMAIN_NAME + url;
    }

    public static BoardImageViewDto of(BoardImage image) {
        BoardImageViewDto ret = new BoardImageViewDto();
        ret.setFilePath(image.getUrl());
        ret.setFullFilePath(S3Service.CLOUD_FRONT_DOMAIN_NAME + image.getUrl());

        return ret;
    }
}
