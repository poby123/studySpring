package com.example.demo.api.controller;

import java.util.List;

import com.example.demo.dto.BoardDto.BoardViewDto;
import com.example.demo.service.BoardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    
    private final BoardService boardService;

    @GetMapping("/v1/gallery")
    public List<BoardViewDto> getList(){
        return boardService.findAll();
    }
}
