package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.Role;
import com.example.demo.repository.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
public class RegistryController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("member", new LoginRequest());
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        memberRepository.save(member);

        return "redirect:/login";
    }

    @Getter
    @Setter
    public static class SignupRequest {
        private String username;
        private String password;
        private Role role = Role.ROLE_MEMBER;
    }


    @Getter
    @Setter
    public static class LoginRequest{
        private String username;
        private String password;
        private Role role;
    }
}
