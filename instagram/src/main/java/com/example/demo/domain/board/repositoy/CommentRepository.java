package com.example.demo.domain.board.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.board.entity.Comment;
import com.example.demo.domain.board.repositoy.querydsl.CommentQueryRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentQueryRepository {

}
