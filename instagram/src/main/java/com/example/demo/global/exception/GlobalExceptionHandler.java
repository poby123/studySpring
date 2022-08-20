package com.example.demo.global.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.global.exception.types.BoardNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public String boardNotFound(BusinessException e, Model model) {
        model.addAttribute("status", e.getErrorCode().getStatus());
        model.addAttribute("message", e.getMessage());
        
        return "error";
    }

}
