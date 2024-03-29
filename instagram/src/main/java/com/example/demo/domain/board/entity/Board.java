package com.example.demo.domain.board.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    String title;

    @Lob
    private String content;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BoardImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval= true, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer hit = 0;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    // == 연관관계 메서드 ==
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void addImage(BoardImage image) {
        this.images.add(image);
        image.setBoard(this);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setBoard(this);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    // == 생성 메서드 ==
    @Builder
    private Board(Member writer, String title, String content) {
        setMember(writer);
        this.title = title;
        this.content = content;
    }

    public static Board createBoard(Member member, String title, String content, List<BoardImage> images) {
        Board ret = new Board(member, title, content);

        for (BoardImage image : images) {
            ret.addImage(image);
        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("writer : " + member.getUsername() + "\n");
        sb.append("content : " + content + "\n");

        for (BoardImage image : images) {
            sb.append("\timages : " + image.getUrl() + "\n");
        }

        return sb.toString();
    }
}
