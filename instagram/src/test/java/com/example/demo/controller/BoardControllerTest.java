package com.example.demo.controller;

import com.example.demo.service.BoardService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardControllerTest {
    
    @Autowired
    BoardService boardService;

    @Test
    void 글쓰기테스트(){
        
    }
}
