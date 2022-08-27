package com.example.demo.domain.board.repositoy.querydsl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.dto.BoardViewDto;

@Repository
public interface BoardQueryRepository {
    public Page<BoardViewDto> findBoardViewDtoPage(Pageable pageable);
    public Optional<BoardViewDto> findBoardViewDto(Long boardId);
}
