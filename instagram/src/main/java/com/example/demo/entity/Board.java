package com.example.demo.entity;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(nullable = false)
    String title;

    @Lob
    private String content;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BoardImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer hit = 0;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    @Builder
    public Board(Member writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    // == 연관관계 메서드 ==

    
    // == 생성 메서드 ==
    public static Board createBoard(Member member, String title, String content, List<BoardImage> images) {
        Board ret = new Board(member, title, content);

        for (BoardImage image : images) {
            ret.addImage(image);
        }

        return ret;
    }


    public void addImage(BoardImage image){
        this.images.add(image);
        image.setBoard(this);
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("writer : " + writer.getUsername() + "\n");
        sb.append("content : " + content + "\n");

        for(BoardImage image: images){
            sb.append("\timages : " + image.getUrl() + "\n");
        }

        return sb.toString();
    }
}
