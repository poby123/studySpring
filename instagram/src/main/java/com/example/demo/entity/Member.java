package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    private String image;

    private String job;

    private String email;

    private String about;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLike> likeBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFollow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "follow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFollow> followings = new ArrayList<>();

    @Builder
    public Member(String username, String name, String password, boolean enabled, String email) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;

        this.image = "";
        this.about = "";
        this.job = "";
        this.enabled = true;
        this.role = Role.ROLE_MEMBER;
    }

    /* 연관관계 메서드 */
    // public void doFollow(Member to){
    //     MemberFollow follow = new MemberFollow(this, to);
    //     this.followings.add(follow);
    //     to.followers.add(follow);
    // }

    /* setter */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
