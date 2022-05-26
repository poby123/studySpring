package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.MemberDto.LoginRequest;
import com.example.demo.dto.MemberDto.SignupRequest;
import com.example.demo.entity.SecurityUser;
import com.example.demo.service.UserDetailsServiceImpl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new LoginRequest());
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new SignupRequest());
        return "signup";
    }

    @GetMapping("/member/{memberUsername}")
    public void getMember(@PathVariable("memberUsername") String username) {
        
    }

    @PutMapping("/follow/{memberUsername}")
    public void follow(@AuthenticationPrincipal SecurityUser principal, @PathVariable("memberUsername") String username){
        userDetailsService.follow(principal.getMember(), username);
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupRequest request, Errors errors, Model model) {
        if(errors.hasErrors()){
            List<String> errorMessages = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toUnmodifiableList());
            model.addAttribute("errors", errorMessages);
            model.addAttribute("member", new SignupRequest());
            return "signup";
        }

        userDetailsService.joinMember(request);

        return "redirect:/login";
    }
}
