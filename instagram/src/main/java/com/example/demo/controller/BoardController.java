package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.BoardDto.BoardSaveDto;
import com.example.demo.dto.BoardDto.BoardViewDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("board", new BoardSaveDto());
        return "board/upload";
    }

    @PostMapping("/upload")
    public String upload(@Valid BoardSaveDto dto, Errors errors, Model model) {
        if(errors.hasErrors()){
            List<String> errorMessages = errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toUnmodifiableList());
            model.addAttribute("errors", errorMessages);
            model.addAttribute("board", new BoardSaveDto());
            return "board/upload";
        }

        Member member = memberRepository.findByUsername("poby123").get();
        boardService.save(member, dto);

        return "redirect:/";
    }

    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable(name = "boardId") Long id, Model model) {
        model.addAttribute("board", boardService.findOne(id));
        return "board/board";
    }

    @GetMapping("/like/{boardId}")
    public String boardLike(@PathVariable(name = "boardId") Long id, Model model){
        Member member = memberRepository.findByUsername("poby123").get();
        boardService.likeBoard(member, id);

        return "redirect:/";
    }

    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable(name = "boardId") Long id) {
        boardService.remove(id);

        return "redirect:/";
    }


}
