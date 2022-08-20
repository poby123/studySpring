package com.example.demo.domain.board.repositoy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b join fetch b.member where b.id =:id")
    Optional<Board> findById(@Param("id") Long id);


    @Query("select b from Board b join fetch b.member")
    List<Board> findAll();
}
