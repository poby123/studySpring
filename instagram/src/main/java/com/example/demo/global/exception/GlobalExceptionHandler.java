package com.example.demo.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.global.result.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INPUT_VALUE_INVALID, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
