package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.demo.entity.Member;
import com.example.demo.entity.Role;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MemberDto {

    @Data
    @NoArgsConstructor
    public static class MemberBoardViewDto {
        private String username;
        private String image;

        public static MemberBoardViewDto of(Member member) {
            MemberBoardViewDto ret = new MemberBoardViewDto();
            ret.setUsername(member.getUsername());
            ret.setImage(member.getImage());

            return ret;
        }
    }

    @Data
    @NoArgsConstructor
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
