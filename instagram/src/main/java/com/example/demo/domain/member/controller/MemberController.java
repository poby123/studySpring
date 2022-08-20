package com.example.demo.domain.member.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.domain.member.dto.MemberDto.LoginRequest;
import com.example.demo.domain.member.dto.MemberDto.MemberProfileViewDto;
import com.example.demo.domain.member.dto.MemberDto.SignupRequest;
import com.example.demo.domain.member.entity.SecurityUser;
import com.example.demo.domain.member.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new LoginRequest());
        return "member/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new SignupRequest());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupRequest request, Errors errors, Model model) {
        if(errors.hasErrors()){
            List<String> errorMessages = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toUnmodifiableList());
            model.addAttribute("errors", errorMessages);
            model.addAttribute("member", new SignupRequest());
            return "member/signup";
        }

        userDetailsService.joinMember(request);

        return "redirect:/login";
    }

    @GetMapping("/member/{memberUsername}")
    public String getMember(@PathVariable("memberUsername") String username, Model model) {
        MemberProfileViewDto dto = userDetailsService.getMemeberToProfileViewDto(username);
        model.addAttribute("member", dto);

        return "member/profile";
    }

    @PutMapping("/follow/{memberUsername}")
    public void follow(@AuthenticationPrincipal SecurityUser principal, @PathVariable("memberUsername") String username){
        userDetailsService.follow(principal.getMember(), username);
    }

}
