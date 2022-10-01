package com.example.demo.domain.member.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.member.dto.MemberFollowDto.FollowerDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MemberDto {

    @Data
    @NoArgsConstructor
    public static class MemberProfileViewDto{
        private String username;
        private String name;
        private String email;
        private String image;
        private String job;
        private String about;

        private List<BoardViewDto> boards = new ArrayList<>();
        private List<MemberBoardViewDto> followers = new ArrayList<>();
        // private List<MemberBoardViewDto> followings = new ArrayList<>();

        @Builder
        public MemberProfileViewDto(String username, String name, String email, String image, String job, String about){
            this.username = username;
            this.name = name;
            this.email = email;
            this.image = image;
            this.job = job;
            this.about = about;
        }

        public static MemberProfileViewDto of(Member member){
            MemberProfileViewDto ret = new MemberProfileViewDto();

            ret.username = member.getUsername();
            ret.name = member.getName();
            ret.email = member.getEmail();
            ret.image = member.getImage();
            ret.job = member.getJob();
            ret.about = member.getAbout();

            ret.boards = member.getBoards().stream().map(BoardViewDto::of).collect(Collectors.toUnmodifiableList());
            ret.followers = member.getFollowers().stream().map(FollowerDto::new).map(dto -> dto.getMember()).collect(Collectors.toUnmodifiableList());
            // ret.followings = member.getFollowings().stream().map(FollowingDto::new).map(dto -> dto.getMember()).collect(Collectors.toUnmodifiableList());

            return ret;
        }
    }


    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
        private Role role;
    }
}
