package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {
    
    @GetMapping("/")
    public String hello(){
        log.info("Hello getMapping!");
        return "index";
    }
}
