package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import com.example.demo.dto.BoardDto.BoardViewDto;
import com.example.demo.dto.BoardDto.BoardSaveDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController {

    private final MemberRepository memberRepository;
    private final BoardService boardService;

    @GetMapping("/")
    public String getBoards(Model model) {
        List<BoardViewDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        return "index";
    }

    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable(name = "boardId") Long id, Model model) {
        model.addAttribute("board", boardService.findOne(id));
        return "board";
    }

    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable(name = "boardId") Long id) {
        boardService.remove(id);

        return "redirect:/";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute BoardSaveDto dto) {
        Member member = memberRepository.findByUsername("하이디").get();
        boardService.save(member, dto);

        return "redirect:/";
    }

}
