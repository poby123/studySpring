package com.example.demo.domain.board.repositoy;

import java.util.List;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.BoardLike;
import com.example.demo.domain.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long>{
    
    public List<BoardLike> findByMemberAndBoard(Member member, Board board);
}
