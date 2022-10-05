package com.example.demo.domain.board.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repositoy.querydsl.BoardQueryRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryRepository {
}
