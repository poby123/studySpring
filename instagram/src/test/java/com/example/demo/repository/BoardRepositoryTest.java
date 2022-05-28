package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import com.example.demo.dto.BoardDto.BoardViewDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void memberProfileViewDtoFetchTest(){
        BoardViewDto dto = boardRepository.findBoardViewDtoById(55L);
        assertEquals("poby123", dto.getWriter().getUsername());
    }
}
