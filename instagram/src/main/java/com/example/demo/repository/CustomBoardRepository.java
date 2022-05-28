package com.example.demo.repository;

import com.example.demo.dto.BoardDto.BoardViewDto;

public interface CustomBoardRepository {
    BoardViewDto findBoardViewDtoById(Long id);
}
