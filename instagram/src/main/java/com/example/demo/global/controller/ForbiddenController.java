package com.example.demo.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbiddenController {

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}
