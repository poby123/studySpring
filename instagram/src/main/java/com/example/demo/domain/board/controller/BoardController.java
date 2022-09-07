package com.example.demo.domain.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.api.service.BoardApiService;
import com.example.demo.domain.board.dto.BoardDto.BoardSaveDto;
import com.example.demo.domain.board.dto.BoardViewDto;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.board.service.S3Service;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repositoy.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController {

    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final BoardApiService boardApiService;

    @GetMapping("/")
    public String getBoards(Model model, @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "0", required = false) int page) {
                
        List<BoardViewDto> boards = boardApiService.getBoardViewDtoPage(size, page).getContent();
        model.addAttribute("s3Domain", S3Service.CLOUD_FRONT_DOMAIN_NAME);
        model.addAttribute("boards", boards);

        return "index";
    }

    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable(name = "boardId") Long boardId, Model model) {
        final BoardViewDto result = boardApiService.getBoardViewDto(boardId);

        model.addAttribute("s3Domain", S3Service.CLOUD_FRONT_DOMAIN_NAME);
        model.addAttribute("board", result);
        return "board/board";
    }

    @GetMapping("/like/{boardId}")
    public String boardLike(@PathVariable(name = "boardId") Long id, Model model) {
        boardService.likeBoard(id);

        return "redirect:/";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("board", new BoardSaveDto());
        return "board/upload";
    }

    @PostMapping("/upload")
    public String upload(@Valid BoardSaveDto dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .collect(Collectors.toUnmodifiableList());
            model.addAttribute("errors", errorMessages);
            model.addAttribute("board", new BoardSaveDto());
            return "board/upload";
        }

        Member member = memberRepository.findByUsername("poby123").get();
        boardService.save(member, dto);

        return "redirect:/";
    }

    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable(name = "boardId") Long id) {
        boardService.remove(id);

        return "redirect:/";
    }

}
