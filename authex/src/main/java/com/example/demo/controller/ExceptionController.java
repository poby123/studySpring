package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {
    
    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }
}
