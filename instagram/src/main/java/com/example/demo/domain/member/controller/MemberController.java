package com.example.demo.domain.member.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.board.service.S3Service;
import com.example.demo.domain.member.dto.LoginRequestDto;
import com.example.demo.domain.member.dto.RegisterRequestDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

// @Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new LoginRequestDto());
        return "member/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new RegisterRequestDto());
        return "member/signup";
    }

    // @PostMapping("/signup")
    // public String signup(@Valid RegisterRequestDto request, Errors errors, Model model) {
    //     if(errors.hasErrors()){
    //         List<String> errorMessages = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toUnmodifiableList());
    //         model.addAttribute("errors", errorMessages);
    //         model.addAttribute("member", new RegisterRequestDto());
    //         return "member/signup";
    //     }

    //     userDetailsService.joinMember(request);

    //     return "redirect:/login";
    // }

    @GetMapping("/member/{memberUsername}")
    public String getMember(@PathVariable("memberUsername") String username, Model model) {
        Member member = memberService.getMember(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
        model.addAttribute("member", member);
        model.addAttribute("s3Domain", S3Service.CLOUD_FRONT_DOMAIN_NAME);

        return "member/profile";
    }

    // @PutMapping("/follow/{memberUsername}")
    // public void follow(@AuthenticationPrincipal SecurityUser principal, @PathVariable("memberUsername") String username){
    //     memberService.follow(principal.getMember(), username);
    // }

}
