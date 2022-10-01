package com.example.demo.domain.member.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.domain.board.entity.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String image;

    private boolean enabled;

    private String job;

    private String about;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFollow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "follow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFollow> followings = new ArrayList<>();

    @Builder
    public Member(String username, String name, String password, String email) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;

        this.image = "";
        this.about = "";
        this.job = "";
        this.enabled = true;
        this.roles = Arrays.asList(new MemberRole(Role.ROLE_MEMBER, this));
    }

    /* 연관관계 메서드 */

    /* setter */
    public void setEncryptedPassword(String password) {
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
