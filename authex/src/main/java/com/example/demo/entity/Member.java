package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String username, String password, boolean enabled, Role role){
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
