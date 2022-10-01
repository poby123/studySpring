package com.example.demo.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tbl_member_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRole {

    @Id
    @GeneratedValue
    @Column(name = "member_role_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JsonIgnore
    private Member member;
    
    public MemberRole(Role role, Member member) {
        this.role = role;
        this.member = member;
    }
}
