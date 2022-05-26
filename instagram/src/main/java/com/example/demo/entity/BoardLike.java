package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLike {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    Board board;

    private BoardLike(Member member, Board board){
        this.member = member;
        this.board = board;
    }

    // == 연관관계 메서드
    public static BoardLike doLike(Member member, Board board){
        BoardLike like = new BoardLike(member, board);
        member.getLikeBoards().add(like);
        board.getLikes().add(like);

        return like;
    }

    public static void undoLike(BoardLike boardLike){
        boardLike.getMember().getLikeBoards().remove(boardLike);
        boardLike.getBoard().getLikes().remove(boardLike);
    }
}
