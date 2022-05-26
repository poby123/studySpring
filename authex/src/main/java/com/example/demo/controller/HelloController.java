package com.example.demo.controller;

import com.example.demo.entity.SecurityUser;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    
    //@GetMapping
    // public String homeV1(Authentication authentication, Model model) {
    //     if (authentication != null) {
    //         SecurityUser principal = (SecurityUser) authentication.getPrincipal();
    //         model.addAttribute("principal", principal.getMember());
    //         model.addAttribute("role", principal.getMember().getRole().getDescription());
    //     }
    //     return "index";
    // }

    @GetMapping("/")
    public String homeV2(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "index";
    }
}
