package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;

public interface CustomBoardRepository {
    
    Optional<Board> findById(@Param("id")Long id);

    Optional<Board> findByIdWithMember(@Param("id")Long id);
}
