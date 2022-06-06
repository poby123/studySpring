package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms")
    public ModelAndView rooms() {
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("list", chatRoomRepository.findAllRooms());

        return mv;
    }

    @PostMapping("/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        rttr.addFlashAttribute("roomName", chatRoomRepository.createChatRoomDto(name).getName());
        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public String getRoom(String roomId, Model model){
        model.addAttribute("room", chatRoomRepository.findByRoomId(roomId));
        return "chat/room";
    }

}
