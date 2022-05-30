package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberFollow {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_id")
    Member follow;

    private MemberFollow(Member from, Member to) {
        this.member = from;
        this.follow = to;
    }

    public static MemberFollow doFollow(Member from, Member to) {
        MemberFollow follow = new MemberFollow(from, to);

        from.getFollowings().add(follow);
        to.getFollowers().add(follow);

        return follow;
    }

    public static MemberFollow unFollow(MemberFollow follow) {
        // MemberFollow follow = new MemberFollow(from, to);

        follow.getMember().getFollowings().remove(follow);
        follow.getFollow().getFollowers().remove(follow);

        return follow;
    }

}
