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


    @Data
    @NoArgsConstructor
    public static class MemberBoardViewDto {
        private String username;
        private String image;

        public MemberBoardViewDto(Member member){
            this.username = member.getUsername();
            this.image = member.getImage();
        }

        public static MemberBoardViewDto of(Member member) {
            MemberBoardViewDto ret = new MemberBoardViewDto();
            ret.setUsername(member.getUsername());
            ret.setImage(member.getImage());

            return ret;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequest {

        @NotBlank(message = "아이디는 필수입력 값입니다.")
        private String username;

        @NotBlank(message = "이름은 필수 입력값입니다.")
        private String name;

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        private String password;

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        private String image;
        private String job;
        private String about;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            Member ret = Member.builder().username(username).name(name)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .build();

            ret.setJob(job);
            ret.setAbout(about);
            ret.setEnabled(true);

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
