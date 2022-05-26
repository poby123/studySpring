package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardLike;
import com.example.demo.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long>{
    
    public List<BoardLike> findByMemberAndBoard(Member member, Board board);
}
