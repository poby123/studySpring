package com.example.demo.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.global.exception.types.BoardNotFoundException;
import com.example.demo.global.result.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public String boardNotFound(BusinessException e, Model model) {
        model.addAttribute("status", e.getErrorCode().getStatus());
        model.addAttribute("message", e.getMessage());
        
        return "error";
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(e.getErrorCode()));
    }

}
