package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, CustomBoardRepository {

    @Query("select b from Board b join fetch b.writer where b.id =:id")
    Optional<Board> findById(@Param("id") Long id);


    @Query("select b from Board b join fetch b.writer")
    List<Board> findAll();
}
